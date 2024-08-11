package com.jdc.spring.trx.repo;

import java.util.List;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;
import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;

public interface BalanceHistoryRepo {

	void create(BalanceHistoryForm history);

	List<UserBalanceInfo> search(UserBalanceSearch search);

}
