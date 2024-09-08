package com.jdc.spring.trx.model.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.model.AccountException;
import com.jdc.spring.trx.model.dto.Account;

public interface AccountService {

	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = AccountException.class, timeout = 1)
	void transfer(int from, int to, int amount);
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	List<Account> add(int amount);
	
	@Transactional(readOnly = true)
	List<Account> findAll();
}
