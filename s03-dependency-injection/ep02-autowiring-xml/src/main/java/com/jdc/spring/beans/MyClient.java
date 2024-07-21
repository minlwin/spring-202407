package com.jdc.spring.beans;

public class MyClient {

	private MyService myService;
	
	public MyClient() {
	}

	public MyClient(MyService myService) {
		super();
		this.myService = myService;
	}

	public void setService(MyService myService) {
		this.myService = myService;
	}

	public String action() {
		return myService.getMessage();
	}
}
