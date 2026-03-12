package com.cumpleanos.ws.config.handler;

import com.cumpleanos.ws.persistence.dto.WsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WsHandler implements WebSocketHandler {

    private final WsBroker broker;
    private final ObjectMapper mapper;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        URI uri = session.getHandshakeInfo().getUri();

        MultiValueMap<String, String> query =
                UriComponentsBuilder.fromUri(uri).build().getQueryParams();

        String user = query.getFirst("user");

        if (user == null || user.isBlank()) {
            log.warn("Conexion WS rechazada: user null");
            return session.close();
        }

        List<String> channels =
                Optional.ofNullable(query.get("channel")).orElse(List.of());

        log.info("Usuario conectado {}", user);

        /* sinks */
        Sinks.Many<WsMessage> userSink = broker.user(user);

        List<Flux<WsMessage>> sources = new ArrayList<>();
        sources.add(userSink.asFlux());

        channels.forEach(ch ->
                sources.add(broker.channel(ch).asFlux())
        );

        /* output websocket */
        Flux<WebSocketMessage> output =
                Flux.merge(sources)
                        .map(msg -> {
                            try {
                                return mapper.writeValueAsString(msg);
                            } catch (Exception e) {
                                return "{}";
                            }
                        })
                        .map(session::textMessage);

        /* input websocket */
        Flux<Void> input =
                session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .flatMap(text -> {

                            try {

                                WsMessage msg =
                                        mapper.readValue(text, WsMessage.class);

                                switch (msg.type()) {

                                    case "GROUP_MESSAGE", "NOTIFICATION" ->
                                            broker.sendChannel(msg.channel(), msg);

                                    case "PRIVATE_MESSAGE" ->
                                            broker.sendUser(msg.target(), msg);
                                }

                            } catch (Exception ignored) {}

                            return Mono.empty();
                        });

        Mono<Void> sessionFlow =
                session.send(output).and(input);

        /* evento conexión */
        channels.forEach(channel ->
                broker.broadcast(
                        channel,
                        new WsMessage(
                                "USER_CONNECTED",
                                channel,
                                "Usuario " + user + " ingresó",
                                "system",
                                user
                        )
                )
        );

        return sessionFlow.doFinally(signal -> {

            log.info("Usuario desconectado {}", user);

            channels.forEach(channel -> {

                broker.broadcast(
                        channel,
                        new WsMessage(
                                "USER_DISCONNECTED",
                                channel,
                                "Usuario " + user + " salió",
                                "system",
                                user
                        )
                );

                broker.removeChannelIfEmpty(channel);
            });

            broker.removeUser(user);

        });
    }
}