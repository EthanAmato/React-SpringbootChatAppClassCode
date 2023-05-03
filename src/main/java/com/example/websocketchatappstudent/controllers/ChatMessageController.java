package com.example.websocketchatappstudent.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.websocketchatappstudent.models.ChatMessage;

@Controller
public class ChatMessageController {
	
	@MessageMapping("/chat") //how we take in STOMP messages from client
	@SendTo("/topic/messages") //where we are going to send our messages to (how the frontend will receive our messages)
	public String handleChatMessage(ChatMessage message) {
		return message.getUser() + ": " + message.getMessage();
	}
	
	 
}
