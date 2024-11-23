package com.jdc.spring.controller.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.spring.model.entity.Course;
import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Course_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CourseSearch(
		Level level,
		String name) {

	public Predicate[] where(CriteriaBuilder cb, Root<Course> root) {
		var params = new ArrayList<Predicate>();
		
		if(level != null) {
			params.add(cb.equal(root.get(Course_.level), level));
		}
		
		if(StringUtils.hasLength(name)) {
			// lower(c.name) like ?
			params.add(cb.like(cb.lower(root.get(Course_.name)), name.toLowerCase().concat("%")));
		}
		
		return params.toArray(size -> new Predicate[size]);
	}
}
