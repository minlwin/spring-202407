package com.jdc.spring.aop.service;

import org.springframework.stereotype.Component;

@Component
public class OtherService {

	public void test() {
		System.out.println("Message from Other Service");
	}
}
