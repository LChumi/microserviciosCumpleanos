package com.cumpleanos.ws.presentation.controller;

import com.cumpleanos.ws.config.handler.NotificationHandler;
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

    @PostMapping("/notify/bradcast")
    public Mono<Void> broadcast(@RequestBody String message) {
        notificationHandler.broadcast("public",message);
        return Mono.empty();
    }
}
