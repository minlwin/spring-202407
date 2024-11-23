package com.jdc.spring.controller.output;

import java.time.LocalDateTime;
import java.util.List;

import com.jdc.spring.model.entity.Course;
import com.jdc.spring.model.entity.Course.Level;

public record CourseDetails(
		int id,
		String name,
		Level level,
		int hours,
		int fees,
		LocalDateTime createdAt,
		String description,
		List<SectionInfo> sections) {

	public static CourseDetails from(Course entity) {
		return new CourseDetails(
				entity.getId(),
				entity.getName(),
				entity.getLevel(),
				entity.getHours(),
				entity.getFees(),
				entity.getCreatedAt(),
				entity.getDescription(),
				entity.getSections()
				.stream().map(SectionInfo::from).toList());
	}

}
