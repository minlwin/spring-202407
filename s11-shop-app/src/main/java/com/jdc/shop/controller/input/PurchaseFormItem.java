package com.jdc.shop.controller.input;

import java.io.Serializable;

import lombok.Data;

@Data
public class PurchaseFormItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String category;
	private String productName;
	private int quantity;
	private int unitPrice;
	
	public int getTotal() {
		return quantity * unitPrice;
	}
}
