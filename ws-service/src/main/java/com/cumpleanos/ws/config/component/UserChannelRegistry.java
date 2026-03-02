package com.cumpleanos.ws.config.component;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserChannelRegistry {

    private final Map<String, Sinks.Many<String>> userChannels = new ConcurrentHashMap<>();

    public Sinks.Many<String> getChannel(String userId) {
        return userChannels.computeIfAbsent(
                userId,
                id -> Sinks.many().multicast().onBackpressureBuffer()
        );
    }

    public void sendToUser(String userId, String message) {
        Sinks.Many<String> sink = userChannels.get(userId);
        if (sink != null) {
            sink.tryEmitNext(message);
        }
    }

    public void removeIfExists(String userId) {
        userChannels.remove(userId);
    }

}