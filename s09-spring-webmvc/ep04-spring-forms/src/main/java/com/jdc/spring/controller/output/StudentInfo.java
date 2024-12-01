package com.jdc.spring.controller.output;

import java.time.LocalDateTime;

import com.jdc.spring.model.entity.Student;
import com.jdc.spring.model.entity.Student.Education;
import com.jdc.spring.model.entity.Student_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record StudentInfo(
		int id,
		String name,
		String phone,
		String email,
		Education education,
		LocalDateTime entryAt) {

	public static void select(CriteriaQuery<StudentInfo> cq, Root<Student> root) {
		cq.multiselect(
			root.get(Student_.id),
			root.get(Student_.name),
			root.get(Student_.phone),
			root.get(Student_.email),
			root.get(Student_.lastEducation),
			root.get(Student_.entryAt)
		);
	}
}
