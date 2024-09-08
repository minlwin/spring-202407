package com.jdc.spring.trx.model.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.spring.trx.model.dto.Account;

public interface AccountRepo {

	List<Account> findAll();
	
	Optional<Account> findById(int id);
	
	int update(int id, int amount);
}
