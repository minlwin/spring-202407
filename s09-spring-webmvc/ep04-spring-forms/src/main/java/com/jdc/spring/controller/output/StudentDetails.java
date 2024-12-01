package com.jdc.spring.controller.output;

import java.time.LocalDateTime;
import java.util.List;

import com.jdc.spring.model.entity.Student;
import com.jdc.spring.model.entity.Student.Education;

public record StudentDetails(
		int id,
		String name,
		String phone,
		String email,
		LocalDateTime entryAt,
		Education education,
		String remark,
		List<RegistrationInfo> registrations) {

	public static StudentDetails from(Student entity) {
		return new StudentDetails(
				entity.getId(), 
				entity.getName(), 
				entity.getPhone(), 
				entity.getEmail(), 
				entity.getEntryAt(), 
				entity.getLastEducation(), 
				entity.getRemark(), 
				entity.getRegistrations().stream()
					.map(RegistrationInfo::from).toList());
	}
}
