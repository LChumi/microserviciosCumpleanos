package com.cumpleanos.meta.persistence.models.telegram.request;

import lombok.Data;

@Data
public class TelegramSendMessageResponse {

    private Boolean ok;

    private TelegramResult result;

    private String description;

}
