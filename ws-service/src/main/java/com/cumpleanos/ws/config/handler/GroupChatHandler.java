package com.cumpleanos.ws.config.handler;

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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupChatHandler implements WebSocketHandler {

    private final Map<String, Sinks.Many<String>> groupChannels = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        URI uri = session.getHandshakeInfo().getUri();
        MultiValueMap<String, String> query = UriComponentsBuilder.fromUri(uri).build().getQueryParams();
        String chatId = query.getFirst("chatId");
        String user = query.getFirst("user");

        Sinks.Many<String> sink = groupChannels.computeIfAbsent(chatId,
                id -> Sinks.many().multicast().onBackpressureBuffer());

        Flux<String> input = session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(msg -> sink.tryEmitNext("[" + chatId + "] " + user + ": " + msg))
                .doFinally(sig -> log.info("Usuario {} salió del grupo {}", user, chatId));

        Flux<WebSocketMessage> output = sink.asFlux().map(session::textMessage);

        return session.send(output).and(input.then());
    }
}

