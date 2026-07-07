package com.cumpleanos.meta.persistence.models.telegram.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TelegramResult {

    @JsonProperty("message_id")
    private Long messageId;

}
