package com.cumpleanos.meta.presentation.controller;

import com.cumpleanos.meta.configuration.properties.TelegramProperties;
import com.cumpleanos.meta.persistence.dto.TelegramAlertRequest;
import com.cumpleanos.meta.persistence.dto.TelegramSendRequest;
import com.cumpleanos.meta.service.implementation.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
public class TelegramNotificationController {

    private final TelegramBotService telegramBotService;
    private final TelegramProperties properties;

    @PostMapping("/send-message")
    public ResponseEntity<Void> sendMessage(@RequestBody TelegramSendRequest request) {
        telegramBotService.sendMessage(request.chatId(), request.text());
        return ResponseEntity.ok().build();
    }

    // opcional: endpoint específico que ya resuelve el chatId del grupo,
    // así el microservicio que llama por Feign ni se preocupa por saber el ID
    @PostMapping("/alert")
    public ResponseEntity<Void> sendAlert(@RequestBody TelegramAlertRequest request) {
        telegramBotService.sendMessage(Long.valueOf(properties.getGroupAdminId()), request.text());
        return ResponseEntity.ok().build();
    }
}
