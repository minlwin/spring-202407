package com.jdc.shop.controller.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;
import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.Purchase.Status;
import com.jdc.shop.model.transaction.entity.PurchasePk_;
import com.jdc.shop.model.transaction.entity.Purchase_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class PurchaseSearch {
	
	private Integer supplierId;
	private Status status;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private String keyword;
	
	public static PurchaseSearch withSupplier(int id) {
		var search = new PurchaseSearch();
		search.setSupplierId(id);
		return search;
	}
	
	public Predicate[] where(
			CriteriaBuilder cb, 
			Root<Purchase> root,
			Join<Purchase, Supplier> supplier) {
		
		var params = new ArrayList<Predicate>();
		
		if(null != supplier) {
			params.add(cb.equal(supplier.get(Supplier_.id), supplierId));
		}
		
		if(null != status) {
			params.add(cb.equal(root.get(Purchase_.status), status));
		}
		
		if(null != dateFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Purchase_.id).get(PurchasePk_.issueAt), dateFrom));
		}
		
		if(null != dateTo) {
			params.add(cb.lessThanOrEqualTo(root.get(Purchase_.id).get(PurchasePk_.issueAt), dateTo));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(supplier.get(Supplier_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(supplier.get(Supplier_.shopName)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Purchase_.remark)), keyword.toLowerCase().concat("%"))
			));
		}

		return params.toArray(size -> new Predicate[size]);
	}
}
