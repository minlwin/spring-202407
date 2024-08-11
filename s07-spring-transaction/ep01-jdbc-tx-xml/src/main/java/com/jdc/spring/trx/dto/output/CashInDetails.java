package com.jdc.spring.trx.dto.output;

import java.time.LocalDateTime;

import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record CashInDetails(
		int id,
		TransactionType trxType,
		LocalDateTime issueAt,
		TransactionStatus status,
		LedgerType ledger,
		String accountId,
		String name,
		UserLevel level,
		int beforeUsb,
		int amount,
		String particular,
		String cashInFrom
		) {

}
