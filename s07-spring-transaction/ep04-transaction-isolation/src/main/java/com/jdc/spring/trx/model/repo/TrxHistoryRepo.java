package com.jdc.spring.trx.model.repo;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TrxHistoryRepo {

	@Transactional(propagation = Propagation.MANDATORY)
	int create(String description, int amount, String status);

	@Transactional(propagation = Propagation.MANDATORY)
	void update(int id, String status);

	@Transactional(propagation = Propagation.MANDATORY)
	void update(int id, String status, String message);

}
