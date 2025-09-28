package com.piotrd.SimpleMessenger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    /*
     * Konfiguracja brokera wiadomości:
     * - setApplicationDestinationPrefixes("/app"): Ustawia prefiks dla wiadomości wysyłanych z klienta do serwera.
     * - enableSimpleBroker("/topic"): Włącza prosty broker wiadomości z prefiksem "/topic" dla wiadomości wysyłanych z serwera do klientów.
     * - enableSimpleBroker("/queue"): Włącza prosty broker wiadomości z prefiksem "/queue" dla wiadomości prywatnych.
     * - setHeartbeatValue(new long[] {10000, 10000}): Ustawia wartość heartbeat na 10 sekund dla obu kierunków
     */

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
    }
}
