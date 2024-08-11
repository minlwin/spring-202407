package com.jdc.spring.trx.repo;

import java.util.Optional;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.output.CashInDetails;

public interface TransactionCashInRepo {

	Optional<CashInDetails> findById(int id);

	void create(int trxId, CashInForm form);

}
