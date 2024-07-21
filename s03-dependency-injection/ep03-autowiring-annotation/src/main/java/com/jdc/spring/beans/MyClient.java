package com.jdc.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyClient {
	
	@Autowired
	private MyService myService;

	public String action() {
		return myService.getMessage();
	}
}
