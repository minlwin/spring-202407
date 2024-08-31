package com.jdc.spring.trx.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;

public interface UserBalanceService {

	@Transactional(readOnly = true)
	List<UserBalanceInfo> search(UserBalanceSearch search);
}
