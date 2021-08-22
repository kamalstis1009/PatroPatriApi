package com.patropatri.api.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * Register STOMP's endpoint
     * We don't need to implement WebSocketHandler, STOMP has done this thing.
     * <p>
     * Register the node (Endpoint) of the STOMP protocol and map it to the specified URL
     * We use STOMP, so there is no need to implement WebSocketHandler.
     * The purpose of implementing WebSocketHandler is to receive and process messages, and STOMP has already done this for us.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the endpoint of STOMP, and use SockJS protocol
        // Register the node of the STOMP protocol and specify the use of the SockJS protocol
        registry.addEndpoint("/im").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }

    /**
     * Configure message broker
     * <p>
     * Configure to use the message broker
     *
     * @param registry message broker registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Configure global message broker, client will subscribe these message brokers to receive messages
        // Unified configuration of the message agent, the message agent is the subscription point, and the client accepts messages through the subscription message agent point
        registry.enableSimpleBroker("/b", "/g", "/user");

        // configure point to point message's prefix
        // Configure the prefix for point-to-point messages
        registry.setUserDestinationPrefix("/user");
    }
}