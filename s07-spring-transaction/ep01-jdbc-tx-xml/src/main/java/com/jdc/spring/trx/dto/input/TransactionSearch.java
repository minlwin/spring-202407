package com.jdc.spring.trx.dto.input;

import java.time.LocalDate;

import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;

public record TransactionSearch(
		TransactionType trxType,
		TransactionStatus status,
		LocalDate from,
		LocalDate to,
		String keyword
		) {

}
