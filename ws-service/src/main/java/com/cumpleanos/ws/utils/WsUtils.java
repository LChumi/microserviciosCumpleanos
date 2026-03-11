package com.cumpleanos.ws.utils;

import com.cumpleanos.ws.persistence.dto.WsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;

import java.time.Duration;

public final class WsUtils {

    private WsUtils() {}

    public static Flux<WebSocketMessage> output(
            WebSocketSession session,
            Flux<WsMessage> source,
            ObjectMapper mapper
    ) {

        return source
                .map(msg -> {
                    try {
                        return mapper.writeValueAsString(msg);
                    } catch (Exception e) {
                        return "{}";
                    }
                })
                .map(session::textMessage);
    }

    public static Flux<WebSocketMessage> heartbeat(WebSocketSession session) {
        return Flux.interval(Duration.ofSeconds(20))
                .map(i -> session.pingMessage(factory ->
                        factory.wrap(new byte[0])
                ));
    }

}