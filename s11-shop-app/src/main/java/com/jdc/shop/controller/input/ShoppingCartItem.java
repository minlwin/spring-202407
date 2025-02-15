package com.jdc.shop.controller.input;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ShoppingCartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private Integer id;
	@NonNull
	private String product;
	@NonNull
	private Integer price;
	
	private int quantity;
	
	public int getTotal() {
		return quantity * price;
	}
	
	public void addOne() {
		++ quantity;
	}

	public void removeOne() {
		-- quantity;
	}

	public static ShoppingCartItem from(ProductForm form) {
		return new ShoppingCartItem(form.getId(), form.getName(), form.getSalePrice());
	}
}
