package com.jdc.spring.security.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.jdc.spring.security.model.entity.Account;
import com.jdc.spring.security.model.entity.Account_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class AccountSearch {

	private Boolean activated;
	private String name;
	private LocalDate requestFrom;
	private LocalDate requestTo;
	
	public Predicate[] where(CriteriaBuilder cb, Root<Account> root) {
		var params = new ArrayList<Predicate>();
		
		var securityContext = SecurityContextHolder.getContext();
		params.add(cb.notEqual(root.get(Account_.email), securityContext.getAuthentication().getName()));
		
		if(null != activated) {
			params.add(cb.equal(root.get(Account_.activated), activated));
		}
		
		if(null != requestFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Account_.requestedAt), requestFrom.atStartOfDay()));
		}
		
		if(null != requestTo) {
			params.add(cb.lessThan(root.get(Account_.requestedAt), requestTo.plusDays(1).atStartOfDay()));
		}

		if(StringUtils.hasLength(name)) {
			params.add(cb.like(cb.lower(root.get(Account_.name)), name.toLowerCase().concat("%")));
		}
		
		return params.toArray(size -> new Predicate[size]);
	}
}
