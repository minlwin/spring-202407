package com.jdc.shop.controller.output;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;
import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.PurchasePk;
import com.jdc.shop.model.transaction.entity.PurchasePk_;
import com.jdc.shop.model.transaction.entity.PurchaseProduct;
import com.jdc.shop.model.transaction.entity.PurchaseProduct_;
import com.jdc.shop.model.transaction.entity.Purchase_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public record PurchaseInfo(
		PurchasePk id,
		String supplierName,
		String shopName,
		String phone,
		String email,
		Long totalItem,
		Long totalAmount) {

	public String code() {
		return id.getCode();
	}
	
	public static void select(
			CriteriaQuery<PurchaseInfo> cq, 
			CriteriaBuilder cb, 
			Root<Purchase> root,
			Join<Purchase, Supplier> supplier,
			Join<Purchase, PurchaseProduct> items) {
		
		cq.multiselect(
			root.get(Purchase_.id),
			supplier.get(Supplier_.name),
			supplier.get(Supplier_.shopName),
			supplier.get(Supplier_.phone),
			supplier.get(Supplier_.email),
			cb.count(items.get(PurchaseProduct_.id)),
			cb.sum(cb.prod(items.get(PurchaseProduct_.quantity), items.get(PurchaseProduct_.buyPrice)))
		);
		
		cq.groupBy(
			root.get(Purchase_.id),
			supplier.get(Supplier_.name),
			supplier.get(Supplier_.shopName),
			supplier.get(Supplier_.phone),
			supplier.get(Supplier_.email)
		);
		
		cq.orderBy(
			cb.desc(root.get(Purchase_.id).get(PurchasePk_.issueAt)),	
			cb.desc(root.get(Purchase_.id).get(PurchasePk_.seqNumber))	
		);
	}
}
