package com.jdc.spring.aop.model;

import java.time.LocalDateTime;

public record RegistrationDto(
		int id,
		String course,
		int fees,
		LocalDateTime registAt,
		String student,
		String phone,
		String email) {

}
