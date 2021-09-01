package com.patropatri.api.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //config.enableSimpleBroker("/topic", "/queue");
        //config.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/g", "/user"); // These are endpoints the client can subscribe to.
        registry.setUserDestinationPrefix("/user"); // Message received with one of those below destinationPrefixes will be automatically router to controllers @MessageMapping
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //.addEndpoint("/api/v3/websocket")
        registry.addEndpoint("stomp").setAllowedOrigins("*").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }
}