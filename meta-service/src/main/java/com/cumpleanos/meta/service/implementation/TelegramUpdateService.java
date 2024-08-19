package com.cumpleanos.meta.service.implementation;

import com.cumpleanos.meta.configuration.properties.TelegramProperties;
import com.cumpleanos.meta.service.handler.TelegramCommandHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUpdateService {

    private final TelegramBotService telegramBotService;
    private final TelegramProperties properties;
    private final List<TelegramCommandHandler> handlers;
    private Map<String, TelegramCommandHandler> commands;

    @PostConstruct
    void init() {
        commands = handlers.stream()
                .collect(Collectors.toMap(
                        h -> h.command().toLowerCase(),
                        Function.identity()));
    }

    @Async
    public void process(Long chatId, String text) {

        try {

            String command = normalizeCommand(text);

            TelegramCommandHandler handler = commands.get(command);

            if (handler == null) {
                telegramBotService.sendMessage(
                        chatId,
                        "Comando no reconocido.\nEscribe /menu para ver las opciones disponibles."
                );
                return;
            }

            String response = handler.handle(chatId, text);

            if (response != null && !response.isBlank()) {
                telegramBotService.sendMessage(chatId, response);
            }

        } catch (Exception e) {

            log.error("Error procesando comando de Telegram", e);

            telegramBotService.sendMessage(
                    chatId,
                    "⚠️ Ocurrió un error procesando tu solicitud."
            );

        }

    }

    private String normalizeCommand(String text) {

        if (text == null || text.isBlank()) {
            return "";
        }

        return text.trim().split("\\s+")[0].toLowerCase();
    }

}