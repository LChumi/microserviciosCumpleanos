package com.cumpleanos.ws.presentation.controller;

import com.cumpleanos.ws.config.handler.NotificationHandler;
import com.cumpleanos.ws.persistence.dto.BroadcastRequest;
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

    private final NotificationHandler notificationHandler;

    @PostMapping("/notify/broadcast")
    public Mono<Void> broadcast(@RequestBody BroadcastRequest request) {
        notificationHandler.broadcast(request);
        return Mono.empty();
    }

}