package com.jdc.spring.trx.repo;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.output.TransferDetails;

public interface TransactionTransferRepo {

	@Transactional(readOnly = true)
	TransferDetails findById(int id);

	@Transactional
	void create(int trxId, String loginId, int amount);

}
