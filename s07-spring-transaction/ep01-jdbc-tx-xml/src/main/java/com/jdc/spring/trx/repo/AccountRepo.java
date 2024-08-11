package com.jdc.spring.trx.repo;

import java.util.Optional;

import com.jdc.spring.trx.dto.output.AccountDto;

public interface AccountRepo {

	Optional<AccountDto> findById(String loginId);

	void updateBalance(String loginId, int balance);

}
