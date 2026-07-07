package com.cumpleanos.meta.persistence.models.telegram.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramSendMessageRequest {

    @JsonProperty("chat_id")
    private Long chatId;

    private String text;

    @JsonProperty("parse_mode")
    private String parseMode;

}