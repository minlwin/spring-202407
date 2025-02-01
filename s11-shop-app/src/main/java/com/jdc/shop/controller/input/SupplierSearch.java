package com.jdc.shop.controller.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record SupplierSearch(
		String phone, 
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Supplier> root) {
		var params = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(phone)) {
			params.add(cb.like(root.get(Supplier_.phone), phone.concat("%")));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(root.get(Supplier_.name)), keyword.concat("%")),
				cb.like(cb.lower(root.get(Supplier_.shopName)), keyword.concat("%"))
			));
		}

		return params.toArray(size -> new Predicate[size]);
	}
}
