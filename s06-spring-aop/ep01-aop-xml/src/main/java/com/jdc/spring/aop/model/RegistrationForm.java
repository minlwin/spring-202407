package com.jdc.spring.aop.model;

public record RegistrationForm(
		String course,
		int fees,
		String student,
		String phone,
		String email) {

}
