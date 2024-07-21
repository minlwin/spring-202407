package com.jdc.spring.beans;

public class MyServiceCustom implements MyService {

	@Override
	public String getMessage() {
		return "Hello from Custom Service";
	}

}
