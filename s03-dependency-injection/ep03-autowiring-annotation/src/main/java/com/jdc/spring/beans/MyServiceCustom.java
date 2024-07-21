package com.jdc.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("custom")
public class MyServiceCustom implements MyService {

	@Override
	public String getMessage() {
		return "Hello from Custom Service";
	}

}
