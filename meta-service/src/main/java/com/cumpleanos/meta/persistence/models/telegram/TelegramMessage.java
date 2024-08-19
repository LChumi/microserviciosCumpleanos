package com.cumpleanos.meta.persistence.models.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TelegramMessage {

    @JsonProperty("message_id")
    private Long messageId;

    private TelegramChat chat;

    private TelegramUser from;

    private String text;

}
