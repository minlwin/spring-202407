package com.jdc.spring.wiring;

public class MyDefaultService implements MyService {

	@Override
	public String getMessage() {
		return "Hello from default service";
	}

}
