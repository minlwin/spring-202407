package com.jdc.spring.trx.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.TransactionSearch;
import com.jdc.spring.trx.dto.output.TransactionInfo;

public interface TransactionService {

	@Transactional(readOnly = true)
	List<TransactionInfo> search(TransactionSearch search);
}
