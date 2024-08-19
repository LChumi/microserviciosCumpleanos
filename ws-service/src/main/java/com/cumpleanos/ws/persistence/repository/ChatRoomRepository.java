package com.cumpleanos.ws.persistence.repository;

import com.cumpleanos.ws.persistence.models.ChatRoom;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ChatRoomRepository extends ReactiveMongoRepository<ChatRoom, String> {
    Mono<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
