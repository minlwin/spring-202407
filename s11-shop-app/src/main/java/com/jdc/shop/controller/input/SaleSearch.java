package com.jdc.shop.controller.input;

import java.time.LocalDate;

public record SaleSearch(
		String customerId,
		LocalDate dateFrom,
		LocalDate dateTo,
		String keyword) {

	public static SaleSearch withCustomerId(String customerId) {
		return new SaleSearch(customerId, null, null, null);
	}
}
