package com.jdc.spring.controller.output;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.spring.model.entity.Course;
import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Course_;
import com.jdc.spring.model.entity.Section_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record CourseInfo(
		int id,
		String name,
		Level level,
		int hours,
		int fees,
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime createdAt,
		long sections) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<CourseInfo> cq, Root<Course> root) {
		
		var sections = root.join(Course_.sections, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Course_.id),
			root.get(Course_.name),
			root.get(Course_.level),
			root.get(Course_.hours),
			root.get(Course_.fees),
			root.get(Course_.createdAt),
			cb.count(sections.get(Section_.id))
		);
		
		cq.groupBy(
			root.get(Course_.id),
			root.get(Course_.name),
			root.get(Course_.level),
			root.get(Course_.hours),
			root.get(Course_.fees),
			root.get(Course_.createdAt)
		);
	}

	public static CourseInfo from(Course entity) {
		return new CourseInfo(entity.getId(), entity.getName(), entity.getLevel(), entity.getHours(), entity.getFees(), entity.getCreatedAt(), entity.getSections().size());
	}

}
