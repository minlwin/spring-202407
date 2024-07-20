package com.jdc.spring.bean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlConfigTest {
	
	static ConfigurableApplicationContext context;

	@BeforeAll
	static void beforeAll() {
		context = new GenericXmlApplicationContext("classpath:/application.xml");
	}
	
	@Test
	void test() {
		var bean = context.getBean("first", MyFirstBean.class);
		assertNotNull(bean);
	}
	
	@AfterAll
	static void afterAll() {
		if(null != context) {
			context.close();
		}
	}
}
