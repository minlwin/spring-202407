package com.jdc.spring.trx.dto.input;

public record CashInForm(
		String account,
		String cashInFrom,
		Integer amount,
		String particular
		) {

}
