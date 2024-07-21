package com.jdc.spring.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class AutowireTest {
	
	@Autowired
	private MyClient client;

	@Test
	void test() {
		var result = client.action();
		System.out.println(result);
	}
}
