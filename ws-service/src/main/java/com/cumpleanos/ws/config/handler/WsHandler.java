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

        Sinks.Many<WsMessage> userSink = broker.user(user);

        Flux<WebSocketMessage> output =
                Flux.merge(
                        userSink.asFlux()
                )
                        .map(msg -> {
                            try{
                                return mapper.writeValueAsString(msg);
                            } catch (Exception e) {
                                return "{}";
                            }
                        })
                        .map(session::textMessage);

        Flux<Void> input=
                session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .flatMap(text -> {
                            WsMessage msg;

                            try {
                                msg = mapper.readValue(text, WsMessage.class);
                            } catch (Exception e) {
                                return Mono.empty();
                            }

                            switch (msg.type()){

                                case "GROUP_MESSAGE", "NOTIFICATION" ->
                                        broker.sendChannel(msg.channel(), msg);

                                case "PRIVATE_MESSAGE" ->
                                        broker.sendUser(msg.target(), msg);

                            }

                            return Mono.empty();
                        });
        return session
                .send(output)
                .and(input);
    }
}
