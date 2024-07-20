package com.jdc.spring.wiring;

public class MyClient {

	private MyService myService;

	public MyClient() {
	}

	public MyClient(MyService myService) {
		super();
		this.myService = myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	public String getMessage() {
		return myService.getMessage();
	}
}
