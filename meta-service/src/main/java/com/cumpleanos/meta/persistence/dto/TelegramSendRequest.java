package com.cumpleanos.meta.persistence.dto;

public record TelegramSendRequest(Long chatId, String text,String parseMode, TelegramAttachment attachment) {}