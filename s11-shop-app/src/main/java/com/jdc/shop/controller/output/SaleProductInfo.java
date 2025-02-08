package com.jdc.shop.controller.output;

public record SaleProductInfo(
		int productId,
		String productName,
		String productCategory,
		String productImage,
		int sellPrice,
		int quantity) {

	public int getTotal() {
		return sellPrice * quantity;
	}
}
