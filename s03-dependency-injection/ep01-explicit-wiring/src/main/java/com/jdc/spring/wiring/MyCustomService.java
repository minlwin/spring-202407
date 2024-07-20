package com.jdc.spring.wiring;

public class MyCustomService implements MyService {

	@Override
	public String getMessage() {
		return "Hello from custom service.";
	}

}
