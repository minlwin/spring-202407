package com.jdc.spring.trx.repo;

import com.jdc.spring.trx.dto.output.TransferDetails;

public interface TransactionTransferRepo {

	TransferDetails findById(int id);

	void create(int trxId, String loginId, int amount);

}
