package com.jdc.spring.trx.repo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;
import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;

@Transactional(readOnly = true)
public interface BalanceHistoryRepo {

	@Transactional
	void create(BalanceHistoryForm history);

	List<UserBalanceInfo> search(UserBalanceSearch search);

}
