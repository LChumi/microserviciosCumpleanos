package com.cumpleanos.meta.service.implementation;

import com.cumpleanos.meta.configuration.properties.WhatsappProperties;
import com.cumpleanos.meta.persistence.models.whatsapp.Change;
import com.cumpleanos.meta.persistence.models.whatsapp.Text;
import com.cumpleanos.meta.persistence.models.whatsapp.WebhookPayLoad;
import com.cumpleanos.meta.persistence.models.whatsapp.request.Context;
import com.cumpleanos.meta.persistence.models.whatsapp.request.SendMessageRequest;
import com.cumpleanos.meta.service.http.WhatsappClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PropertySource("classpath:meta.properties")
@Slf4j
public class WebhookServiceImpl {

    private final WhatsappProperties prop;
    private final WhatsappClient whatsappClient;

    public void processIncomingMessage(WebhookPayLoad payload) {
        log.info("Mensaje de webhook entrante {}", payload);
        var message = payload.entry().stream()
                .flatMap(entry -> entry.changes().stream())
                .map(Change::value)
                .flatMap(value -> value.messages().stream())
                .findFirst()
                .orElse(null);

        if (message != null && "text".equals(message.type())) {
            var phoneNumberId = payload.entry().getFirst().changes().getFirst().value().metadata().phone_number_id();

            var replyMessage = new SendMessageRequest(
                    "whatsapp",
                    message.from(),
                    new Text("Echo:" + message.text().body()),
                    new Context(message.id())
            );
            whatsappClient.sendMessage("Bearer "+prop.getGraphApiToken(), replyMessage , phoneNumberId);

            markMessageAsRead(phoneNumberId, message.id());
        }
    }

    private void markMessageAsRead(String phoneNumberId, String messageId) {
        var markAsReadRequest = Map.of(
                "messaging_product", "whatsapp",
                "status", "read",
                "message_id", messageId
        );
        whatsappClient.sendMessage("Bearer "+ prop.getGraphApiToken(),markAsReadRequest, phoneNumberId);
    }
}
