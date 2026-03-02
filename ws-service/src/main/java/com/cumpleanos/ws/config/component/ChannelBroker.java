package com.cumpleanos.ws.config.component;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChannelBroker {

    private final Map<String, Sinks.Many<String>> canales = new ConcurrentHashMap<>();

    public Sinks.Many<String> getChannel(String name) {
        return canales.computeIfAbsent(
                name,
                k -> Sinks.many().multicast().onBackpressureBuffer()
        );
    }

    public void broadcast(String canal, String message) {
        getChannel(canal).tryEmitNext(message);
    }
}
