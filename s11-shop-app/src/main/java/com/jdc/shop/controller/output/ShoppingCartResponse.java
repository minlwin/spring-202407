package com.jdc.shop.controller.output;

import java.util.List;

import com.jdc.shop.controller.input.ShoppingCart;
import com.jdc.shop.controller.input.ShoppingCartItem;

public record ShoppingCartResponse(
		List<ShoppingCartItem> items,
		int totalCount,
		int totalAmount) {

	public static ShoppingCartResponse from(ShoppingCart cart) {
		return new ShoppingCartResponse(cart.getItems(), cart.getTotalCount(), cart.getTotalAmount());
	}
}
