package com.jdc.spring.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.spring.JavaConfig;

@SpringJUnitConfig(classes = JavaConfig.class)
public class JavaConfigAutowireTest {

	@Autowired
	private MyClient client;

	@Test
	void test() {
		var result = client.action();
		System.out.println(result);
	}

}
