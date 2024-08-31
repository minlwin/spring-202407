package com.jdc.spring.trx.dto.output;

import java.time.LocalDateTime;

import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record UserBalanceInfo(
		UserLevel level,
		String accountId,
		String name,
		int trxId,
		LocalDateTime issueAt,
		TransactionType trxType,
		LedgerType ledger,
		String particular,
		int beforeAmount,
		int trxAmount
		) {

	public int getUserBalance() {
		return ledger == LedgerType.Credit 
				? beforeAmount + trxAmount 
				: beforeAmount - trxAmount;
	}
}
