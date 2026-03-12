package com.cumpleanos.ws.config.handler;

import com.cumpleanos.ws.persistence.dto.WsMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WsBroker {

    private final Map<String, Sinks.Many<WsMessage>> channels =
            new ConcurrentHashMap<>();

    private final Map<String, Sinks.Many<WsMessage>> users =
            new ConcurrentHashMap<>();

    public Sinks.Many<WsMessage> channel(String name) {

        return channels.computeIfAbsent(
                name,
                k -> Sinks.many().multicast().directBestEffort()
        );
    }

    public Sinks.Many<WsMessage> user(String user) {

        return users.computeIfAbsent(
                user,
                k -> Sinks.many().multicast().directBestEffort()
        );
    }

    public void broadcast(String channel, WsMessage msg) {

        if (channel == null || channel.isBlank()) return;

        channel(channel).tryEmitNext(msg);
    }

    public void sendChannel(String channel, WsMessage msg) {
        channel(channel).tryEmitNext(msg);
    }

    public void sendUser(String user, WsMessage msg) {
        user(user).tryEmitNext(msg);
    }

    public void removeUser(String user) {
        users.remove(user);
    }

    public void removeChannelIfEmpty(String name) {

        Sinks.Many<WsMessage> sink = channels.get(name);

        if (sink != null && sink.currentSubscriberCount() <= 0) {
            channels.remove(name);
        }
    }
}