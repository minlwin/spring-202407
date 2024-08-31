package com.jdc.spring.trx.repo;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.output.CashInDetails;

public interface TransactionCashInRepo {

	@Transactional(readOnly = true)
	Optional<CashInDetails> findById(int id);

	@Transactional
	void create(int trxId, CashInForm form);

}
