package com.cumpleanos.ws.config.handler;

import com.cumpleanos.ws.config.component.ChannelBroker;
import com.cumpleanos.ws.config.component.RedisSessionRegistry;
import com.cumpleanos.ws.persistence.dto.BroadcastRequest;
import com.cumpleanos.ws.service.implemetation.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationHandler implements WebSocketHandler {

    private final UserService userService;
    private final ChannelBroker broker;
    private final RedisSessionRegistry sessionRegistry;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        UriComponents uri = UriComponentsBuilder
                .fromUri(session.getHandshakeInfo().getUri())
                .build();

        String nickname = uri.getQueryParams().getFirst("user");
        String canal = Optional
                .ofNullable(uri.getQueryParams().getFirst("canal"))
                .orElse("public");

        Sinks.Many<String> sink = broker.getChannel(canal);

        Mono<Void> onConnect =
                userService.login(nickname)
                        .flatMap(user ->
                                sessionRegistry.connect(nickname)
                        )
                        .doOnSuccess(v -> {
                            log.info("Usuario conectado {} al canal {} ", nickname, canal);
                            sink.tryEmitNext("Usuario " + nickname + " conectado");
                        })
                        .then();

        Flux<String> input = session.receive()
    .filter(msg -> msg.getType() == WebSocketMessage.Type.TEXT)
    .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(message -> {
                    log.info("Cliente {} envió a canal {}: {}", nickname, canal, message);
                    sink.tryEmitNext("[" + canal + "] " + nickname + ": " + message);
                });

        Flux<WebSocketMessage> output =
                sink.asFlux().map(session::textMessage);

        Flux<WebSocketMessage> heartbeat =
                Flux.interval(Duration.ofSeconds(20))
                        .map(i -> session.pingMessage(factory ->
                                factory.wrap(new byte[0])
                        ));

        return onConnect
                .then(session.send(Flux.merge(output, heartbeat))
                        .and(input.then()))
                .then(
                        sessionRegistry.disconnect(nickname)
                                .flatMap(remaining -> {

                                    if (remaining <= 0) {
                                        return userService.logout(nickname)
                                                .then();
                                    }

                                    return Mono.empty();
                                })
                );
    }

    public void broadcast(BroadcastRequest request) {
        broker.broadcast(request.cannal(), request.message());
    }
}