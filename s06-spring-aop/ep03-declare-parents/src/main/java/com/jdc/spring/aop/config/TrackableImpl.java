package com.jdc.spring.aop.config;

import org.springframework.stereotype.Component;

@Component
public class TrackableImpl implements Trackable {

	@Override
	public void track() {
		System.out.println("Tracking");
	}

}
