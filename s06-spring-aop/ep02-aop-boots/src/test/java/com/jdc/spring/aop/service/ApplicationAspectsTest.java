package com.jdc.spring.aop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.aop.controller.Controller1;

@SpringBootTest
public class ApplicationAspectsTest {

	@Autowired
	private Controller1 controller;
	@Autowired
	private Service1 service1;
	@Autowired
	private Service2 service2;
	
	@Test
	void test() {
		controller.methodOne("Testing");
		
		service1.methodOne();
		service1.methodOne("Hello");
		service1.methodOne("Hello", "Spring AOP");
		
		service2.methodOne();
	}
}
