package com.jdc.spring.beans;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdc.spring.BeanConfiguration;

public class JavaBaseConfigTest {
	
	static ConfigurableApplicationContext context;

	@BeforeAll
	static void beforeAll() {
		context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	}
	
	@Test
	void test() {
		var names = context.getBeanDefinitionNames();
		System.out.println(Arrays.asList(names));
		
		var aliases = context.getAliases("strBuilder");
		System.out.println(Arrays.asList(aliases));
	}
	
	@AfterAll
	static void afterAll() {
		if(null != context) {
			context.close();
		}
	}
}
