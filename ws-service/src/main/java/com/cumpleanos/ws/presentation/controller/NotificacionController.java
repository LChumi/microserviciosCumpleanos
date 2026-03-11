package com.cumpleanos.ws.presentation.controller;

import com.cumpleanos.ws.config.handler.WsBroker;
import com.cumpleanos.ws.persistence.dto.BroadcastRequest;
import com.cumpleanos.ws.persistence.dto.WsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ws")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class NotificacionController {

    private final WsBroker broker;

    @PostMapping("/broadcast")
    public Mono<Void> broadcast(@RequestBody BroadcastRequest request) {

        if (request.channel() == null || request.channel().isBlank()) {
            return Mono.error(new IllegalArgumentException("canal requerido"));
        }

        broker.broadcast(
                request.channel(),
                new WsMessage(
                        "CHANNEL_MESSAGE",
                        request.channel(),
                        request.message(),
                        "system",
                        null
                )
        );

        return Mono.empty();
    }

}