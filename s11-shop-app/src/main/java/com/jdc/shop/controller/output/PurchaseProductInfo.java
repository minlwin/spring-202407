package com.jdc.shop.controller.output;

import com.jdc.shop.model.transaction.entity.PurchaseProduct;

public record PurchaseProductInfo(
		int productId,
		String productName,
		int sellPrice,
		int buyPrice,
		int quantity,
		int lastStock) {

	public static PurchaseProductInfo from(PurchaseProduct entity) {
		return new PurchaseProductInfo(
				entity.getProduct().getId(), 
				entity.getProduct().getName(), 
				entity.getSellPrice(), 
				entity.getBuyPrice(), 
				entity.getQuantity(), 
				entity.getBeforeStock());
	}
}
