package com.jdc.spring.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.spring.BeanConfiguration;

@SpringJUnitConfig(classes = BeanConfiguration.class)
public class LifecycleTest {

	@Autowired
	private MyClient client;
	
	@Test
	void test() {
		System.out.println(client.readMessage());
	}
}
