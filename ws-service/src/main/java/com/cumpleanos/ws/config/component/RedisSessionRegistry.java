package com.cumpleanos.ws.config.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RedisSessionRegistry {

    @Qualifier("reactiveRedisTemplate")
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public RedisSessionRegistry(@Qualifier("reactiveRedisTemplate") ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String key(String userId) {
        return "ws:sessions:" + userId;
    }

    public Mono<Long> connect(String userId) {
        return redisTemplate.opsForValue()
                .increment(key(userId));
    }

    public Mono<Long> disconnect(String userId) {
        return redisTemplate.opsForValue()
                .decrement(key(userId))
                .flatMap(val -> {
                    if (val <= 0) {
                        return redisTemplate.delete(key(userId))
                                .thenReturn(0L);
                    }
                    return Mono.just(val);
                });
    }
}