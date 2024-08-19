package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.app.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {

    Optional<Session> findBySessionId(String sessionId);

    List<Session> findByUserId(String userId);

    Optional<Session> findTopByUserIdOrderByLoginTimeDesc(String userId);
}