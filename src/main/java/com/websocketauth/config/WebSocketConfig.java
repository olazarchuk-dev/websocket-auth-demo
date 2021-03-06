package com.websocketauth.config;

import com.websocketauth.interceptor.WebSocketHandshakeAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    final WebSocketHandshakeAuthInterceptor webSocketHandshakeAuthInterceptor;

    @Autowired
    public WebSocketConfig(WebSocketHandshakeAuthInterceptor webSocketHandshakeAuthInterceptor) {
        this.webSocketHandshakeAuthInterceptor = webSocketHandshakeAuthInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/messages");
        registry.setApplicationDestinationPrefixes("/api");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/websocket/connect")
                .addInterceptors(webSocketHandshakeAuthInterceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
