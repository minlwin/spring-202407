package com.jdc.shop.controller.output;

import java.util.List;

import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.Purchase.Status;
import com.jdc.shop.model.transaction.entity.PurchasePk;

public record PurchaseDetails(
		PurchasePk id,
		Status status,
		String remark,
		SupplierInfo supplier,
		List<PurchaseProductInfo> products) {
	
	public int getAllTotal() {
		return products.stream().mapToInt(a -> a.quantity() * a.buyPrice()).sum();
	}

	public static PurchaseDetails from(Purchase entity) {
		return new PurchaseDetails(
				entity.getId(), 
				entity.getStatus(), 
				entity.getRemark(), 
				SupplierInfo.from(entity.getSupplier()), 
				entity.getProducts().stream().map(PurchaseProductInfo::from).toList());
	}
}
