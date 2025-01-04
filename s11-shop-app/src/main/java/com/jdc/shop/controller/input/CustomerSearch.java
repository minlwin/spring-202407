package com.jdc.shop.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Account_;
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
		
		if(null != to) {
			params.add(cb.lessThan(root.get(Customer_.registeredAt), to.plusDays(1).atStartOfDay()));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(root.get(Customer_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Customer_.phone)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Customer_.account).get(Account_.email)), keyword.toLowerCase().concat("%"))
			));
		}

		return params.toArray(size -> new Predicate[size]);
	}
}
