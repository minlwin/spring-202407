package com.jdc.spring.trx.service;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.output.CashInDetails;

public interface CashInService {

	int cashIn(CashInForm form);
	CashInDetails findById(int id);
}
