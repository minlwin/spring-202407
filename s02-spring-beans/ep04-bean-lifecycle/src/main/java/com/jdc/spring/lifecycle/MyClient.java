package com.jdc.spring.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class MyClient {
	
	private String message;
	
	@Autowired
	private MyService service;
	
	@PostConstruct
	private void initialize() {
		message = service.getMessage();
	}
	
	@PreDestroy
	private void realeaseResources() {
		System.out.println("Destroy Beans");
	}
	
	public String readMessage() {
		return message;
	}
}
