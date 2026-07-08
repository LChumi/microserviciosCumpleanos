package com.cumpleanos.meta.service.implementation;

import com.cumpleanos.meta.configuration.properties.TelegramProperties;
import com.cumpleanos.meta.persistence.models.telegram.request.TelegramSendMessageRequest;
import com.cumpleanos.meta.persistence.models.telegram.request.TelegramSendMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBotService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final TelegramProperties properties;

    public void sendMessage(Long chatId, String text) {

        String url =
                "https://api.telegram.org/bot"
                        + properties.getBotToken()
                        + "/sendMessage";

        TelegramSendMessageRequest request =
                TelegramSendMessageRequest.builder()
                        .chatId(chatId)
                        .text(text)
                        .parseMode("HTML")
                        .build();

        TelegramSendMessageResponse response =
                restTemplate.postForObject(
                        url,
                        request,
                        TelegramSendMessageResponse.class
                );

        log.info("Telegram response: {}", response);
    }
}