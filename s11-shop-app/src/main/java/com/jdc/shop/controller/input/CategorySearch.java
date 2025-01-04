package com.jdc.shop.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CategorySearch(
		LocalDate from, 
		LocalDate to,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Category> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != from) {
			params.add(cb.greaterThanOrEqualTo(root.get(Category_.createdAt), from.atStartOfDay()));
		}
		
		if(null != to) {
			params.add(cb.lessThan(root.get(Category_.createdAt), to.plusDays(1).atStartOfDay()));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.like(cb.lower(root.get(Category_.name)), keyword.toLowerCase().concat("%")));
		}

		return params.toArray(size -> new Predicate[size]);
	}
}
