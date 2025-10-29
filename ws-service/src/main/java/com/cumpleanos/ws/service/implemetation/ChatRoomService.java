package com.cumpleanos.ws.service.implemetation;

import com.cumpleanos.ws.persistence.models.ChatRoom;
import com.cumpleanos.ws.persistence.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Mono<ChatRoom> getOrCreateChatRoom(String senderId, String recipientId){
        // define chatId determinístico (ordenado) para evitar duplicados sender/recipient vs recipient/sender
        String chatId = Stream.of(senderId, recipientId).sorted().collect(Collectors.joining("_"));
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .switchIfEmpty(chatRoomRepository.findBySenderIdAndRecipientId(recipientId, senderId))
                .switchIfEmpty(Mono.defer(() -> {
                    ChatRoom room = ChatRoom.builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();
                    return chatRoomRepository.save(room);
                }));
    }
}