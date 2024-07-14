package com.jdc.spring.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {

	public String sayHello() {
		return "Hello IoC Container";
	}
}
