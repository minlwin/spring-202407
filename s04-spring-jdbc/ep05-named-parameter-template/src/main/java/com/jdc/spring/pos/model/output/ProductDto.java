package com.jdc.spring.pos.model.output;

public record ProductDto(
		int id,
		String name,
		int categoryId,
		String categoryName,
		int amount,
		String unit,
		int price) {

}
