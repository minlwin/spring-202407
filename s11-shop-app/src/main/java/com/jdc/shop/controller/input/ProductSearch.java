package com.jdc.shop.controller.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;
import com.jdc.shop.model.master.entity.Category_;
import com.jdc.shop.model.master.entity.Product;
import com.jdc.shop.model.master.entity.ProductStockHistory;
import com.jdc.shop.model.master.entity.ProductStockHistory.Action;
import com.jdc.shop.model.master.entity.ProductStockHistoryPk_;
import com.jdc.shop.model.master.entity.ProductStockHistory_;
import com.jdc.shop.model.master.entity.ProductStock_;
import com.jdc.shop.model.master.entity.Product_;
import com.jdc.shop.model.transaction.entity.Purchase;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record ProductSearch(
		Integer supplierId,
		String keyword,
		Integer stockFrom,
		Integer stockTo) {
	
	public static ProductSearch withSupplier(int supplier) {
		return new ProductSearch(supplier, null, null, null);
	}

	public Predicate[] where(
			CriteriaBuilder cb, Root<Product> root, 
			Join<Purchase, Supplier> supplier, 
			ListJoin<Product, ProductStockHistory> stockHistory) {
		
		var params = new ArrayList<Predicate>();
		
		params.add(cb.equal(stockHistory.get(ProductStockHistory_.id).get(ProductStockHistoryPk_.action), Action.Buy));
		
		if(null != supplierId) {
			params.add(cb.equal(supplier.get(Supplier_.id), supplierId));
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
