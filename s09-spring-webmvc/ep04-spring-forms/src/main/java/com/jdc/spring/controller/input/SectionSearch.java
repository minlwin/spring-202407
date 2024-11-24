package com.jdc.spring.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Course_;
import com.jdc.spring.model.entity.Section;
import com.jdc.spring.model.entity.Section_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record SectionSearch(
		Level level,
		LocalDate startFrom,
		LocalDate startTo,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Section> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != level) {
			params.add(cb.equal(root.get(Section_.course).get(Course_.level), level));
		}
		
		if(null != startFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Section_.startDate), startFrom));
		}

		if(null != startTo) {
			params.add(cb.lessThanOrEqualTo(root.get(Section_.startDate), startTo));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.like(cb.lower(root.get(Section_.course).get(Course_.name)), keyword.toLowerCase().concat("%")));
		}

		return params.toArray(size -> new Predicate[size]);
	}
}
