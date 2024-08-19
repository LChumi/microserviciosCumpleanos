package com.cumpleanos.ws.persistence.repository;

import com.cumpleanos.ws.persistence.models.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
    Flux<ChatMessage> findByChatId(String chatId);
}
