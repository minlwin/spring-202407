package com.jdc.spring.pos.model.input;

public record ProductForm(
		String name,
		int categoryId,
		int amount,
		String unit,
		int price) {

}
