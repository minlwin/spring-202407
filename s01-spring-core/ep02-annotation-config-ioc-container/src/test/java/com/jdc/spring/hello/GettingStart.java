package com.jdc.spring.hello;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GettingStart {

	@Test
	void loadTest() {
		
		try(var context = new AnnotationConfigApplicationContext()) {
			
			context.scan("com.jdc.spring");
			
			context.refresh();
			
			var bean = context.getBean(HelloBean.class);
			
			System.out.println(bean.sayHello());
		}
	}
}
