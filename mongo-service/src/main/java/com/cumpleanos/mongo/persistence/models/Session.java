package com.cumpleanos.mongo.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sessions")
public class Session implements Serializable {

    @Id
    private String id;

    private String sessionId;
    private String userId;
    private String ipAddress;
    private String userAgent;
    private Instant loginTime;
    private Instant lastActivityTime;
    private boolean active;

    // Para TTL dinámico
    private Instant expireAt;
}

/**
 * Crear TTL dinamico
 * db.sessions.createIndex(
 *   { "expireAt": 1 },
 *   { expireAfterSeconds: 0 }
 * )
 */