package com.example.websocketchatappstudent.models;

public class ChatMessage {

	private String user;
	private String message;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChatMessage [user=" + user + ", message=" + message + "]";
	}

	public ChatMessage(String user, String message) {
		this.user = user;
		this.message = message;
	}

}
