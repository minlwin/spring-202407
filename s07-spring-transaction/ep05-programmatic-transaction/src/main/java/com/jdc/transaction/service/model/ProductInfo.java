package com.jdc.transaction.service.model;

public record ProductInfo(
		int id, 
		String name, 
		int unitPrice, 
		int categoryId, 
		String categroy) {

}
