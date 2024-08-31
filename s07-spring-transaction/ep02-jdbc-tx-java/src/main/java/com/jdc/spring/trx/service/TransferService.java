package com.jdc.spring.trx.service;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.TransferForm;
import com.jdc.spring.trx.dto.output.TransferDetails;

public interface TransferService {

	@Transactional
	int transfer(TransferForm form);
	
	@Transactional(readOnly = true)
	TransferDetails findById(int id);
	
}
