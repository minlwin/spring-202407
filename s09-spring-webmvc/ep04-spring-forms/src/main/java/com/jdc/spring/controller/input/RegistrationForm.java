package com.jdc.spring.controller.input;

import java.time.LocalDate;

import com.jdc.spring.model.entity.Section;

public record RegistrationForm(
		String id,
		int sectionId,
		String course,
		LocalDate startAt,
		int fees,
		Integer studentId,
		String name,
		String phone,
		String email) {

	public static RegistrationForm from(Section entity) {
		return new RegistrationForm(
				null,
				entity.getId(), 
				entity.getCourse().getName(), 
				entity.getStartDate(), 
				0, 
				null, null, null, null);
	}
}
