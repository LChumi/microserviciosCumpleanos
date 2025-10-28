package com.cumpleanos.ws.service.implemetation;

import com.cumpleanos.ws.persistence.dto.ChatMessagePayload;
import com.cumpleanos.ws.persistence.models.ChatMessage;
import com.cumpleanos.ws.persistence.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public Mono<ChatMessage> saveMessage(ChatMessagePayload payload) {
        return chatRoomService.getOrCreateChatRoom(payload.getSenderId(), payload.getRecipientId())
                .flatMap(room -> {
                    ChatMessage m = ChatMessage.builder()
                            .chatId(room.getChatId())
                            .senderId(payload.getSenderId())
                            .recipientId(payload.getRecipientId())
                            .content(payload.getContent())
                            .createdAt(Instant.now())
                            .build();
                    return chatMessageRepository.save(m);
                });
    }

    public Flux<ChatMessage> getMessagesFor(String a, String b) {
        String chatId = Stream.of(a, b).sorted().collect(Collectors.joining("_"));
        return chatMessageRepository.findByChatId(chatId).sort(Comparator.comparing(ChatMessage::getCreatedAt));
    }
}

