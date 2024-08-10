package com.jdc.spring.trx.service;

import java.util.List;

import com.jdc.spring.trx.dto.input.TransactionSearch;
import com.jdc.spring.trx.dto.output.TransactionInfo;

public interface TransactionService {

	List<TransactionInfo> search(TransactionSearch search);
}
