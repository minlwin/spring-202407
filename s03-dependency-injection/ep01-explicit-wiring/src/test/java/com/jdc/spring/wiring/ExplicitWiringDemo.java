package com.jdc.spring.wiring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class ExplicitWiringDemo {

	@Autowired
	private MyClient defaultClient;
	
	@Autowired
	private MyClient customClient;
	
	@Test
	void test() {
		
		System.out.println(defaultClient.getMessage());
		System.out.println(customClient.getMessage());
	}
}
