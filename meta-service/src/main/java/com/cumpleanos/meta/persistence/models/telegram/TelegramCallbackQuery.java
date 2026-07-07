package com.cumpleanos.meta.persistence.models.telegram;

import lombok.Data;

@Data
public class TelegramCallbackQuery {

    private String id;

    private TelegramUser from;

    private String data;

    private TelegramMessage message;

}