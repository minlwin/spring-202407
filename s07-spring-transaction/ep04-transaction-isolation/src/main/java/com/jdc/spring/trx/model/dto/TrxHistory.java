package com.jdc.spring.trx.model.dto;

import java.time.LocalDateTime;

public record TrxHistory(
		int id,
		String description,
		int trxAmount,
		String trxStatus,
		LocalDateTime trxAt) {

}
