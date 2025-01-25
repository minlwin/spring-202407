package com.jdc.shop.controller.input;

import java.io.Serializable;

import org.springframework.util.StringUtils;

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
	
	public boolean validate() {
		return StringUtils.hasLength(category) 
				&& StringUtils.hasLength(productName)
				&& quantity > 0
				&& buyPrice > 0
				&& sellPrice > 0;
	}
}
