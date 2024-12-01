package com.jdc.spring.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.spring.model.entity.Student;
import com.jdc.spring.model.entity.Student.Education;
import com.jdc.spring.model.entity.Student_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record StudentSearch(
		Education education,
		LocalDate entryFrom,
		LocalDate entryTo,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Student> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != education) {
			params.add(cb.equal(root.get(Student_.lastEducation), education));
		}
		
		if(null != entryFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Student_.entryAt), entryFrom.atStartOfDay()));
		}
		
		if(null != entryTo) {
			params.add(cb.lessThan(root.get(Student_.entryAt), entryTo.plusDays(1).atStartOfDay()));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(root.get(Student_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Student_.phone)), keyword.toLowerCase().concat("%"))
			));
		}

		return params.toArray(size -> new Predicate[size]);
	}
}
