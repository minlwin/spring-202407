package com.jdc.shop.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Customer_;
import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.entity.SalePk_;
import com.jdc.shop.model.transaction.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record SaleSearch(
		String customerId,
		Status status,
		LocalDate dateFrom,
		LocalDate dateTo,
		String keyword) {
	
	public Predicate[] where(CriteriaBuilder cb, Root<Sale> root) {
		var params = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(customerId)) {
			params.add(cb.equal(root.get(Sale_.customer).get(Customer_.id), UUID.fromString(customerId)));
		}
		
		if(null != status) {
			params.add(cb.equal(root.get(Sale_.status), status));
		}
		
		if(null != dateFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Sale_.id).get(SalePk_.issueAt), dateFrom));
		}
		
		if(null != dateTo) {
			params.add(cb.lessThanOrEqualTo(root.get(Sale_.id).get(SalePk_.issueAt), dateTo));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(root.get(Sale_.remark)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Sale_.customer).get(Customer_.name)), keyword.toLowerCase().concat("%"))
			));
		}
		
		return params.toArray(size -> new Predicate[size]);
	}

	public static SaleSearch withCustomerId(String customerId) {
		return new SaleSearch(customerId, null, null, null, null);
	}
}
