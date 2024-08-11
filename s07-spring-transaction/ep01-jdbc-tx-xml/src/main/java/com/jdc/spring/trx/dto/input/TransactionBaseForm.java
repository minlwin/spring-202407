package com.jdc.spring.trx.dto.input;

import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionType;

public record TransactionBaseForm(
		TransactionType type,
		LedgerType ledger,
		String account,
		int beforeAmount,
		int amount,
		String particular) {

}
