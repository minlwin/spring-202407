package com.jdc.spring.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Course_;
import com.jdc.spring.model.entity.Registration;
import com.jdc.spring.model.entity.Registration_;
import com.jdc.spring.model.entity.Section_;
import com.jdc.spring.model.entity.Student_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record RegistrationSearch(
		Level level,
		LocalDate from,
		LocalDate to,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Registration> root) {
		var params = new ArrayList<Predicate>();
		
		
		if(null != level) {
			params.add(cb.equal(root.get(Registration_.section).get(Section_.course).get(Course_.level), level));
		}
		
		if(null != from) {
			params.add(cb.greaterThanOrEqualTo(root.get(Registration_.registedAt), from.atStartOfDay()));
		}
		
		if(null != to) {
			params.add(cb.lessThan(root.get(Registration_.registedAt), to.plusDays(1).atStartOfDay()));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(root.get(Registration_.student).get(Student_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Registration_.student).get(Student_.phone)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Registration_.section).get(Section_.course).get(Course_.name)), keyword.toLowerCase().concat("%"))
			));
		}
		
		return params.toArray(size -> new Predicate[size]);
	}
}
