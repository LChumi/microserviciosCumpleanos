package com.cumpleanos.ws.config.handler;

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

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class PrivateNotificationHandler implements WebSocketHandler {

    private final ChatMessageService chatMessageService;
    private final ObjectMapper objectMapper; // Jackson inyectado
    // canales por usuario
    private final Map<String, Sinks.Many<String>> userChannels = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String raw = session.getHandshakeInfo().getUri().getQuery(); // e.g. user=luis
        String userId = Optional.ofNullable(raw).orElse("").replace("user=", "");

        // crear o recuperar canal del usuario
        Sinks.Many<String> sink = userChannels.computeIfAbsent(userId,
                id -> Sinks.many().multicast().onBackpressureBuffer());

        // cuando este WS recibe mensajes desde cliente
        Flux<Void> inputProcessing = session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(text -> {
                    // parse JSON a ChatMessagePayload
                    ChatMessagePayload payload;
                    try {
                        payload = objectMapper.readValue(text, ChatMessagePayload.class);
                    } catch (JsonProcessingException e) {
                        log.warn("Payload inválido: {}", text);
                        return Mono.empty();
                    }
                    // guardar mensaje en DB
                    return chatMessageService.saveMessage(payload)
                            .doOnSuccess(saved -> {
                                // enviar al receptor (si conectado)
                                Sinks.Many<String> recipientSink = userChannels.get(payload.getRecipientId());
                                String jsonToSend;
                                try {
                                    jsonToSend = objectMapper.writeValueAsString(saved);
                                } catch (JsonProcessingException e) {
                                    jsonToSend = saved.getContent();
                                }
                                if (recipientSink != null) {
                                    recipientSink.tryEmitNext(jsonToSend);
                                } // si recipientSink==null -> usuario offline (mensaje ya está en DB)

                                // opcional: enviar eco al emisor conectado
                                Sinks.Many<String> senderSink = userChannels.get(payload.getSenderId());
                                if (senderSink != null) {
                                    senderSink.tryEmitNext(jsonToSend);
                                }
                            }).then();
                }).doFinally(signal -> {
                    // cuando la conexión se cierra, puedes limpiar si quieres
                    // no remover el canal si quieres entregar futuros mensajes cuando se reconecte;
                    // si quieres eliminarlo: userChannels.remove(userId)
                    userChannels.remove(userId);
                    log.info("WS privado desconectado {}", userId);
                });

        // flujo de salida: enviar todo lo que llegue al sink de este usuario
        Flux<WebSocketMessage> output = sink.asFlux()
                .map(session::textMessage);

        return session.send(output).and(inputProcessing.then());
    }

    // utilidad para enviar al usuario desde código (ej. desde servicio)
    public void sendToUser(String userId, String messageJson) {
        var sink = userChannels.get(userId);
        if (sink != null) sink.tryEmitNext(messageJson);
    }
}
