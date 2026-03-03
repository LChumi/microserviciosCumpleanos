package com.cumpleanos.ws.config.handler;

import com.cumpleanos.ws.config.component.RedisSessionRegistry;
import com.cumpleanos.ws.config.component.UserChannelRegistry;
import com.cumpleanos.ws.persistence.dto.ChatMessagePayload;
import com.cumpleanos.ws.service.implemetation.ChatMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PrivateNotificationHandler implements WebSocketHandler {

    private final ChatMessageService chatMessageService;
    private final ObjectMapper objectMapper;
    private final UserChannelRegistry registry;
    private final RedisSessionRegistry sessionRegistry;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        String raw = session.getHandshakeInfo().getUri().getQuery();
        String userId = Optional.ofNullable(raw).orElse("").replace("user=", "");

        Sinks.Many<String> sink = registry.getChannel(userId);

        // contar sesión activa
        sessionRegistry.connect(userId);

        Flux<Void> inputProcessing = session.receive()
                .filter(msg -> msg.getType() == WebSocketMessage.Type.TEXT)
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(text -> {

                    ChatMessagePayload payload;

                    try {
                        payload = objectMapper.readValue(text, ChatMessagePayload.class);
                    } catch (JsonProcessingException e) {
                        log.warn("Payload inválido: {}", text);
                        return Mono.empty();
                    }

                    return chatMessageService.saveMessage(payload)
                            .flatMap(saved -> {

                                String jsonToSend;
                                try {
                                    jsonToSend = objectMapper.writeValueAsString(saved);
                                } catch (JsonProcessingException e) {
                                    jsonToSend = saved.getContent();
                                }

                                // enviar al receptor
                                registry.sendToUser(payload.getRecipientId(), jsonToSend);

                                // eco al emisor
                                registry.sendToUser(payload.getSenderId(), jsonToSend);

                                return Mono.empty();
                            });
                });

        Flux<WebSocketMessage> output =
                sink.asFlux().map(session::textMessage);

        Flux<WebSocketMessage> heartbeat =
                Flux.interval(Duration.ofSeconds(20))
                        .map(i -> session.pingMessage(factory ->
                                factory.wrap(new byte[0])
                        ));

        return session.send(Flux.merge(output, heartbeat))
                .and(inputProcessing.then())
                .then(
                        sessionRegistry.disconnect(userId)
                                .flatMap(remaining -> {
                                    if (remaining <= 0) {
                                        registry.removeIfExists(userId);
                                        log.info("Usuario privado desconectado completamente {}", userId);
                                    }
                                    return Mono.empty();
                                })
                );
    }
}