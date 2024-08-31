package com.jdc.spring.trx.dto.input;

public record TransferForm(
		String from,
		String to,
		int amount,
		String particular) {

}
