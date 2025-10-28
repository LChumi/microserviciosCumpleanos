package com.cumpleanos.ws.presentation.controller;

import com.cumpleanos.ws.persistence.dto.ChatMessagePayload;
import com.cumpleanos.ws.persistence.models.ChatMessage;
import com.cumpleanos.ws.service.implemetation.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ws")
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class ChatController {

    private final ChatMessageService chatMessageService;

    @PostMapping("/chat/send")
    public Mono<ChatMessage> sendMessage(@RequestBody ChatMessagePayload message){
        return chatMessageService.saveMessage(message);
    }

    @GetMapping("/chat/messages/{senderId}/{recipientId}")
    public Flux<ChatMessage> getMessages(@PathVariable String senderId, @PathVariable String recipientId){
        return chatMessageService.getMessagesFor(senderId, recipientId);
    }
}
