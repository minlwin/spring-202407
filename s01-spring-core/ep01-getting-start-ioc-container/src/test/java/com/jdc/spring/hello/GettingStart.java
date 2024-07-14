package com.jdc.spring.hello;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class GettingStart {

	@Test
	void loadTest() {
		
		// Create IoC Container
		try(var context = new GenericXmlApplicationContext()) {
			// Load Configuration File
			context.load("classpath:/application.xml");
			
			// Refresh IoC Container (Initialize Beans)
			context.refresh();
			
			var bean = context.getBean("helloBean");
			
			if(bean instanceof HelloBean helloBean) {
				var message = helloBean.sayHello();
				System.out.println(message);
			}
			
		}
		
	}
	
	@Test
	void loadTest2() {
		
		try(var context = new GenericXmlApplicationContext("classpath:/application.xml")) {
			var helloBean = context.getBean("helloBean", HelloBean.class);
			System.out.println(helloBean.sayHello());
		}
		
	}
}
