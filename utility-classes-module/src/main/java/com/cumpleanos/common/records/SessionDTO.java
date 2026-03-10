package com.cumpleanos.common.records;

import java.time.Instant;

public record SessionDTO(
        String id,
        String sessionId,
        String userId,
        String ipAddress,
        String userAgent,
        Instant loginTime,
        Instant lastActivityTime,
        boolean active,
        Instant expireAt
) {}
