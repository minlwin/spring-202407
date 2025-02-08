package com.jdc.shop.controller.input;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShoppingCartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String product;
	private int quantity;
	private int price;
	
	public int getTotal() {
		return quantity * price;
	}
	
	public void addOne() {
		++ quantity;
	}

	public void removeOne() {
		-- quantity;
	}
}
