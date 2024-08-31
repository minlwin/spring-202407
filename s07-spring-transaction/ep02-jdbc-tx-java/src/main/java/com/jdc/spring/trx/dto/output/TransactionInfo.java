package com.jdc.spring.trx.dto.output;

import java.time.LocalDateTime;

import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record TransactionInfo(
		int id,
		TransactionType trxType,
		TransactionStatus status,
		LocalDateTime issueAt,
		UserLevel level,
		String accountId,
		String name,
		int amount,
		String particular) {

}
