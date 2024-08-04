package com.jdc.spring.aop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.aop.config.Trackable;

@SpringBootTest
public class DeclareParentsAspectTest {
	
	@Autowired
	private MyService myService;

	@Test
	void test() {
		myService.doService();
		
		if(myService instanceof Trackable track) {
			track.track();
		}
	}
}
