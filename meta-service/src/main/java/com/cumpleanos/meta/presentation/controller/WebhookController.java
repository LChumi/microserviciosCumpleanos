package com.cumpleanos.meta.presentation.controller;

import com.cumpleanos.meta.persistence.models.WebhookPayLoad;
import com.cumpleanos.meta.service.implementation.WebhookServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("meta")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WebhookController {

    private final WebhookServiceImpl webhookService;
    @Value("${webhook.verify.token}")
    String verifyToken;

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
        if ("subscribe".equals(mode) && verifyToken.equals(token)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
