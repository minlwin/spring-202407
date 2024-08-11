package com.jdc.spring.trx.repo;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.output.AccountDto;
import com.jdc.spring.trx.utils.constants.TransactionStatus;

public interface TransactionBaseRepo {

	int create(CashInForm form, AccountDto account);

	void update(int trxId, TransactionStatus success);

}
