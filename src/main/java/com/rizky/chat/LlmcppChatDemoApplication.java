package com.rizky.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rizky.chat.services.ChatServices;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class LlmcppChatDemoApplication {

	@Autowired
	ChatServices chatService;
	public static void main(String[] args) {
		SpringApplication.run(LlmcppChatDemoApplication.class, args);
		
	}
	
	@PostConstruct
	public void init()	{
		chatService.startChatService();
	}
	
	

}
