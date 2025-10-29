package com.cumpleanos.ws.config;

import com.cumpleanos.ws.config.handler.GroupChatHandler;
import com.cumpleanos.ws.config.handler.NotificationHandler;
import com.cumpleanos.ws.config.handler.PrivateNotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebSocketConfig {

    private final NotificationHandler publicHandler;
    private final PrivateNotificationHandler privateHandler;
    private final GroupChatHandler groupHandler;

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = Map.of(
                "/ws/notify", publicHandler,
                "/ws/private", privateHandler,
                "/ws/group", groupHandler
        );
        return new SimpleUrlHandlerMapping(map, 1);
    }


    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
