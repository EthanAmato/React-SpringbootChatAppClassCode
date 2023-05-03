package com.example.websocketchatappstudent.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic"); //all messages that have a prefix of /topic will be handled by this message broker 
		config.setApplicationDestinationPrefixes("/app"); //messages sent by clients that start with /app will be routed to the message
															//handling method in our messagecontroller
	}
	
	
	@Override 
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint("/chat") //clients use this endpoint to connect with websocket server
			.setAllowedOriginPatterns("*")
			.withSockJS()
			.setClientLibraryUrl("//cdn.jsdelivr.net/sockjs/latest/sockjs.min.js");
	}
	
}
