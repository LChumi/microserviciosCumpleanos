package com.cumpleanos.meta.presentation.controller;

import com.cumpleanos.meta.configuration.properties.WhatsappProperties;
import com.cumpleanos.meta.persistence.models.telegram.TelegramUpdate;
import com.cumpleanos.meta.persistence.models.whatsapp.WebhookPayLoad;
import com.cumpleanos.meta.service.implementation.TelegramUpdateService;
import com.cumpleanos.meta.service.implementation.WebhookServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("meta")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WebhookController {

    private final WhatsappProperties prop;
    private final WebhookServiceImpl webhookService;
    private final TelegramUpdateService telegramUpdateService;


    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(@RequestBody WebhookPayLoad payLoad) {
        webhookService.processIncomingMessage(payLoad);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/webhook")
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge){

        log.info("verificar Webhook modo {} desafío o challenge {}", mode, challenge);
        if ("subscribe".equals(mode) && prop.getVerifyToken().equals(token)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/telegram/webhook")
    public ResponseEntity<Void> webhook(
            @RequestBody TelegramUpdate update) {

        try {
            if (update == null || update.getMessage() == null || update.getMessage().getChat() == null) {
                return ResponseEntity.ok().build();
            }

            Long chatId = update.getMessage().getChat().getId();
            String text = update.getMessage().getText();

            telegramUpdateService.process(chatId, text);

        } catch (Exception e) {
            log.error("Error webhook Telegram", e);
        }

        return ResponseEntity.ok().build();

    }
}