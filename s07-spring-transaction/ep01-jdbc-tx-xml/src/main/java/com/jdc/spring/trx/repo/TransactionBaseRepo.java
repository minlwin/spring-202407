package com.jdc.spring.trx.repo;

import com.jdc.spring.trx.dto.input.TransactionBaseForm;
import com.jdc.spring.trx.utils.constants.TransactionStatus;

public interface TransactionBaseRepo {

	int create(TransactionBaseForm form);

	void updateStatus(int trxId, TransactionStatus success);

}
