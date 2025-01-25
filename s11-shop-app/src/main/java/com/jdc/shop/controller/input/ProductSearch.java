package com.jdc.shop.controller.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;
import com.jdc.shop.model.master.entity.Category_;
import com.jdc.shop.model.master.entity.Product;
import com.jdc.shop.model.master.entity.ProductStock_;
import com.jdc.shop.model.master.entity.Product_;
import com.jdc.shop.model.transaction.entity.Purchase;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record ProductSearch(
		Integer supplier,
		String keyword,
		Integer stockFrom,
		Integer stockTo) {

	public Predicate[] where(CriteriaBuilder cb, Root<Product> root, Join<Purchase, Supplier> supplier) {
		var params = new ArrayList<Predicate>();
		
		if(null != supplier) {
			params.add(cb.equal(supplier.get(Supplier_.id), supplier));
		}
		
		if(null != stockFrom) {
			params.add(cb.ge(root.get(Product_.stock).get(ProductStock_.stock), stockFrom));
		}
		
		if(null != stockTo) {
			params.add(cb.le(root.get(Product_.stock).get(ProductStock_.stock), stockTo));
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
				cb.like(cb.lower(root.get(Product_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Product_.category).get(Category_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(supplier.get(Supplier_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(supplier.get(Supplier_.shopName)), keyword.toLowerCase().concat("%"))
			));
		}
		
		return params.toArray(size -> new Predicate[size]);
	}
}
