package com.cumpleanos.meta.service.handler;

import org.springframework.stereotype.Component;

@Component
public class MenuCommandHandler implements TelegramCommandHandler {

    @Override
    public String command() {
        return "/menu";
    }

    @Override
    public String handle(Long chatId, String texto) {

        return """
                /ventas
                /certificados
                /cumpleanos
                """;

    }

}