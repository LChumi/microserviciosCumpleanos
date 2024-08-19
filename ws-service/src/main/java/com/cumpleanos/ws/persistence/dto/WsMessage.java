package com.cumpleanos.ws.persistence.dto;

public record WsMessage (
        String type,
        String channel,
        String message,
        String user,
        String target
) {}