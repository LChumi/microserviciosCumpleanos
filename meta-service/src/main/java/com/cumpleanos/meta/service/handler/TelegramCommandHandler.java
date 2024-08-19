package com.cumpleanos.meta.service.handler;

public interface TelegramCommandHandler {

    String command();

    String handle(Long chatId, String texto);

}