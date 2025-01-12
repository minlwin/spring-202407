package com.jdc.shop.controller.input;

public record ProductSearch(
		String keyword,
		Integer stockFrom,
		Integer stockTo) {

}
