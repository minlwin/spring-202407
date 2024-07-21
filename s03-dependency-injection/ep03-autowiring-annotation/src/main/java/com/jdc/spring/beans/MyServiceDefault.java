package com.jdc.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("default")
public class MyServiceDefault implements MyService{

	@Override
	public String getMessage() {
		return "Hello from Default Service";
	}

}
