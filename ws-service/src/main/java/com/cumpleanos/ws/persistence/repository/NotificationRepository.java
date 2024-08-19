package com.cumpleanos.ws.persistence.repository;

import com.cumpleanos.ws.persistence.models.ChatNotification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotificationRepository extends ReactiveMongoRepository<ChatNotification, String> {}

