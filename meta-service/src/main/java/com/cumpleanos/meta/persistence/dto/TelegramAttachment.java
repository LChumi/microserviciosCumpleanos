package com.cumpleanos.meta.persistence.dto;

import lombok.Builder;

@Builder
public record TelegramAttachment(
        String fileName,
        String contentType,
        byte[] content,
        String url
) {}
