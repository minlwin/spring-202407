package com.jdc.spring.trx.repo;

import java.time.LocalDate;

import com.jdc.spring.trx.dto.input.TransactionBaseForm;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;

public interface TransactionBaseRepo {

	int create(TransactionBaseForm form);

	void updateStatus(int trxId, TransactionStatus success);

	Long findTotalAmount(String userId, TransactionType type, LocalDate now);

}
