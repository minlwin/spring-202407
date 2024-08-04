package com.jdc.spring.aop.controller;

import org.springframework.stereotype.Controller;

@Controller
public class Controller1 {

	public void methodOne() {
		System.out.println("No arg method for controller 1");
	}

	public void methodOne(String value) {
		System.out.printf("One arg method for controller 1 : %s%n", value);
	}
	
	public int testing(String argument) {
		return argument.length();
	}

}
