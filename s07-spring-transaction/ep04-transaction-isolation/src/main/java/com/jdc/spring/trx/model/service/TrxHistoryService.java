package com.jdc.spring.trx.model.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TrxHistoryService {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	int createHistory(String formatted, int amount, String string);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void updateStatus(int id, String string);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void updateStatus(int historyId, String string, String message);

}
