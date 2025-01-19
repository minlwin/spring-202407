package com.jdc.shop.controller.input;

import java.io.Serializable;

import lombok.Data;

@Data
public class PurchaseFormItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String category;
	private String productName;
	private int quantity;
	private int buyPrice;
	private int sellPrice;
	
	public int getTotal() {
		return quantity * buyPrice;
	}
}
