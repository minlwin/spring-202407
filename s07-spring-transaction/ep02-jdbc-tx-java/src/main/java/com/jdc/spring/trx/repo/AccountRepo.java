package com.jdc.spring.trx.repo;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.output.AccountDto;

@Transactional(readOnly = true)
public interface AccountRepo {

	Optional<AccountDto> findById(String loginId);

	@Transactional
	void updateBalance(String loginId, int balance);

}
