package com.cumpleanos.ws.config.handler;

import com.cumpleanos.ws.service.implemetation.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class NotificationHandler implements WebSocketHandler {

    private final UserService userService;

    // Canales públicos por tema
    private final Map<String, Sinks.Many<String>> canales = new ConcurrentHashMap<>();

    private Sinks.Many<String> getCanal(String nombre) {
        return canales.computeIfAbsent(nombre, k -> Sinks.many().multicast().onBackpressureBuffer());
    }


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        //Obtener nickname y canal del query param (ws://localhost:8085/ws/notify?user=luis&canal=tramites)
        UriComponents uri = UriComponentsBuilder.fromUri(session.getHandshakeInfo().getUri()).build();
        String nickname = uri.getQueryParams().getFirst("user");
        String canal = Optional.ofNullable(uri.getQueryParams().getFirst("canal")).orElse("public");

        Sinks.Many<String> sink = getCanal(canal);

        Mono<Void> onConnect = userService.login(nickname)
                .doOnSuccess( usuario -> {
                    log.info("Usuario conectado {}", nickname);
                    sink.tryEmitNext("Usuario " + nickname + " conectado");
                })
                .then();

        Flux<String> input = session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(mensaje -> {
                    log.info("Cliente {} envió a canal {}: {}", nickname, canal, mensaje);
                    sink.tryEmitNext("[" + canal + "] " + nickname + ": " + mensaje);
                })
                .publishOn(Schedulers.boundedElastic())
                .doFinally(signal -> {
                    userService.logout(nickname)
                            .doOnSuccess(usuario -> sink.tryEmitNext("Usuario " + nickname + " se desconectó"))
                            .subscribe();
                });

        //Flujo de salida (Todos los mensajes publicos)
        Flux<WebSocketMessage> output = sink.asFlux().map(session::textMessage);

        return onConnect.then(session.send(output).and(input.then()));
    }

    public void broadcast(String canal ,String mensaje){
        getCanal(canal).tryEmitNext(mensaje);
    }
}
