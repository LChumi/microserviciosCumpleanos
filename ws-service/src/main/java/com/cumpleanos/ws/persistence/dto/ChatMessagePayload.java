package com.cumpleanos.ws.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessagePayload {
    private String senderId;
    private String recipientId;
    private String content;
}