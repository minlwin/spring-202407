package com.jdc.spring.lifecycle;

import org.springframework.stereotype.Component;

@Component
public class MyService {

	public String getMessage() {
		return "Hello Spring Bean";
	}

}
