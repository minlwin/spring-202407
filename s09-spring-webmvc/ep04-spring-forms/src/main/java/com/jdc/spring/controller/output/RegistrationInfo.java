package com.jdc.spring.controller.output;

import java.time.LocalDateTime;

import com.jdc.spring.model.entity.Registration;

public record RegistrationInfo(
		int studentId,
		String studentName,
		String studentPhone,
		String studentEmail,
		LocalDateTime entryAt,
		LocalDateTime registedAt) {
	
	public static RegistrationInfo from(Registration entity) {
		return new RegistrationInfo(entity.getStudent().getId(), entity.getStudent().getName(), entity.getStudent().getPhone(), entity.getStudent().getEmail(), entity.getStudent().getEntryAt(), entity.getRegistedAt());
	}

}
