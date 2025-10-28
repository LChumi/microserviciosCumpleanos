package com.cumpleanos.ws.config.handler;

import com.cumpleanos.ws.service.implemetation.UserService;
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
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class NotificationHandler implements WebSocketHandler {

    private final UserService userService;

    //Canal publico
    private final Sinks.Many<String> publicSink = Sinks.many().multicast().onBackpressureBuffer();


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        //Obtener nickname del query param (ws://localhost:8085/ws/notify?user=user)
        String nickname = session.getHandshakeInfo().getUri().getQuery().replace("user=", "");

        Mono<Void> onConnect = userService.login(nickname)
                .doOnSuccess( usuario -> {
                    log.info("Usuario conectado {}", nickname);
                    publicSink.tryEmitNext("Usuario " + nickname + " conectado");
                })
                .then();

        Flux<String> input = session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext( mensaje -> {
                    log.info("Cliente {} envio {}", nickname, mensaje);
                    publicSink.tryEmitNext("Mesnaje de "+ nickname + ":" + mensaje);
                })
                .publishOn(Schedulers.boundedElastic())
                .doFinally( signal -> {
                    userService.logout(nickname)
                            .doOnSuccess( usuario ->  publicSink.tryEmitNext("Usuario "+ nickname + " se desconecto"))
                            .subscribe();
                });

        //Flujo de salida (Todos los mensajes publicos)
        Flux<WebSocketMessage> output = publicSink.asFlux()
                .map(session::textMessage);

        return onConnect.then(session.send(output).and(input.then()));
    }

    public void broadcast(String mensaje){
        publicSink.tryEmitNext(mensaje);
    }
}
