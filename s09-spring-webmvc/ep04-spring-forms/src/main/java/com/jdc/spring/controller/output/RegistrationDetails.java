package com.jdc.spring.controller.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Registration;
import com.jdc.spring.model.entity.RegistrationPk;
import com.jdc.spring.model.entity.Student.Education;

public record RegistrationDetails(
		RegistrationPk id,
		Level level,
		String course,
		LocalDate startAt,
		int fees,
		int seats,
		String name,
		String phone,
		String email,
		Education lastEducation,
		LocalDateTime entryAt,
		LocalDateTime registeredAt,
		String remark
		) {

	public static RegistrationDetails from(Registration entity) {
		return new RegistrationDetails(
				entity.getId(), 
				entity.getSection().getCourse().getLevel(), 
				entity.getSection().getCourse().getName(), 
				entity.getSection().getStartDate(), 
				entity.getSection().getFees(), 
				entity.getSection().getAvailableSeats(),
				entity.getStudent().getName(), 
				entity.getStudent().getPhone(), 
				entity.getStudent().getEmail(), 
				entity.getStudent().getLastEducation(), 
				entity.getStudent().getEntryAt(),
				entity.getRegistedAt(),
				entity.getRemark());
	}
}
