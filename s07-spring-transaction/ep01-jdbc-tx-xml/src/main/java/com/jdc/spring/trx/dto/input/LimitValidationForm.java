package com.jdc.spring.trx.dto.input;

import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record LimitValidationForm(
		String userId,
		UserLevel level,
		TransactionType type,
		int userBalance,
		int trxAmount
		) {

}
