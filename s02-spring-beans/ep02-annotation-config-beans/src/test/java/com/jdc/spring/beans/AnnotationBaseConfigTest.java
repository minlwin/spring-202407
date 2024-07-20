package com.jdc.spring.beans;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBaseConfigTest {

	static ConfigurableApplicationContext applicationContext;
	
	@BeforeAll
	static void beforeAll() {
		applicationContext = new AnnotationConfigApplicationContext("com.jdc.spring.beans");
	}
	
	@Test
	void test() {
		
		var bean = applicationContext.getBean(MyFirstBean.class);
		
		assertNotNull(bean);
	}
	
	@AfterAll
	static void afterAll() {
		if(null != applicationContext) {
			applicationContext.close();
		}
	}
}
