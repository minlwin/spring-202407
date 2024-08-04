package com.jdc.spring.aop.service;

import org.springframework.stereotype.Service;

@Service
public class Service1 {

	public void methodOne() {
		System.out.println("No arg method for service1");
	}
	
	public void methodOne(String value) {
		System.out.printf("One arg method for service1 : %s%n", value);
	}
	
	public void methodOne(String value1, String value2) {
		System.out.println("Two args method for service 1");
	}
}
