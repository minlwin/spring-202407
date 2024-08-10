package com.jdc.spring.trx.service;

import com.jdc.spring.trx.dto.input.TransferForm;
import com.jdc.spring.trx.dto.output.TransferDetails;

public interface TransferService {

	int transfer(TransferForm form);
	TransferDetails findById(int id);
	
}
