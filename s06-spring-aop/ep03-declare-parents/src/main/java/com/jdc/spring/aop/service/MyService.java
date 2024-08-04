package com.jdc.spring.aop.service;

import org.springframework.stereotype.Component;

@Component
public class MyService {

	public void doService() {
		System.out.println("My Service is worked");
	}
}
