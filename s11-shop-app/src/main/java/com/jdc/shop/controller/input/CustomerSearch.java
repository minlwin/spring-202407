package com.jdc.shop.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.entity.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CustomerSearch(
		LocalDate from, 
		LocalDate to,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Customer> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != from) {
			params.add(cb.greaterThanOrEqualTo(root.get(Customer_.registeredAt), from.atStartOfDay()));
		}
		
		return params.toArray(size -> new Predicate[size]);
	}
}
