package com.cumpleanos.meta.service.implementation;

import com.cumpleanos.meta.configuration.properties.TelegramProperties;
import com.cumpleanos.meta.persistence.dto.TelegramSendRequest;
import com.cumpleanos.meta.persistence.models.telegram.request.TelegramSendMessageRequest;
import com.cumpleanos.meta.persistence.models.telegram.request.TelegramSendMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBotService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final TelegramProperties properties;
    private final String BASE_URL = "https://api.telegram.org/bot";

    public void sendMessage(Long chatId, String text) {

        String url = BASE_URL + properties.getBotToken() + "/sendMessage";

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

    public void sendMessage(TelegramSendRequest request) {

        if (request.attachment() == null) {
            sendText(request);
            return;
        }

        String contentType = request.attachment().contentType();

        if (contentType != null && contentType.startsWith("image/")) {
            sendMultipart(request, "sendPhoto", "photo");
        } else {
            sendMultipart(request, "sendDocument", "document");
        }
    }

    private void sendText(TelegramSendRequest request) {

        String url = BASE_URL + properties.getBotToken() + "/sendMessage";

        TelegramSendMessageRequest body =
                TelegramSendMessageRequest.builder()
                        .chatId(request.chatId())
                        .text(request.text())
                        .parseMode(
                                Optional.ofNullable(request.parseMode()).orElse("HTML"))
                        .build();

        restTemplate.postForObject(url, body, TelegramSendMessageResponse.class);
    }

    private void sendMultipart(TelegramSendRequest request,
                               String endpoint,
                               String fieldName) {

        String url = BASE_URL + properties.getBotToken() + "/" + endpoint;

        ByteArrayResource resource =
                new ByteArrayResource(request.attachment().content()) {
                    @Override
                    public String getFilename() {
                        return request.attachment().fileName();
                    }
                };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("chat_id", request.chatId().toString());
        body.add(fieldName, resource);
        body.add("caption", request.text());
        body.add("parse_mode",
                Optional.ofNullable(request.parseMode()).orElse("HTML"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> entity =
                new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, entity, String.class);
    }

}