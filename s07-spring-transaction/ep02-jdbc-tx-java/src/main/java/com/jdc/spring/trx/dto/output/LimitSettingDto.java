package com.jdc.spring.trx.dto.output;

import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record LimitSettingDto(
		UserLevel userLevel,
		String trxType,
		int minLimit,
		int maxLimit,
		int dailyLimit) {

	public TransactionType getTransactionType() {
		return TransactionType.fromDbName(trxType);
	}
}
