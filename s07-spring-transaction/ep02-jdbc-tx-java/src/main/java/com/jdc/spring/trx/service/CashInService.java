package com.jdc.spring.trx.service;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.output.CashInDetails;

public interface CashInService {

	@Transactional
	int cashIn(CashInForm form);
	
	@Transactional(readOnly = true)
	CashInDetails findById(int id);
}
