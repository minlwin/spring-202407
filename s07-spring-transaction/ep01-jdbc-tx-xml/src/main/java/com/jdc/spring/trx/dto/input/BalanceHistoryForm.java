package com.jdc.spring.trx.dto.input;

import com.jdc.spring.trx.utils.constants.LedgerType;

public record BalanceHistoryForm(
		int trxId,
		String accountId,
		int beforeAmount,
		int trxAmount,
		LedgerType ledger,
		String particular) {

}
