package com.jdc.spring.controller.output;

import java.time.LocalDate;
import java.util.List;

import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Section;

public record SectionDetails(
		int id,
		int courseId,
		Level level,
		String courseName,
		LocalDate startAt,
		int months,
		int fees,
		int seats,
		List<RegistrationInfo> registrations) {

	public static SectionDetails from(Section entity) {
		return new SectionDetails(
				entity.getId(), 
				entity.getCourse().getId(),
				entity.getCourse().getLevel(), 
				entity.getCourse().getName(), 
				entity.getStartDate(), 
				entity.getMonths(), 
				entity.getFees(), 
				entity.getAvailableSeats(), 
				entity.getRegistrations().stream().map(RegistrationInfo::from).toList());
	}

}
