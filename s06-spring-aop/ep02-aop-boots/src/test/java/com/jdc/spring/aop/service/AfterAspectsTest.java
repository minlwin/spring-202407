package com.jdc.spring.aop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.aop.controller.Controller1;

@SpringBootTest
public class AfterAspectsTest {

	@Autowired
	private Controller1 controller;
	
	@Test
	void test() {
		
		controller.methodOne();
		
		controller.testing(null);
	}
}
