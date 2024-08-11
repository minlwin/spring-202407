package com.jdc.spring.trx.repo;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;

public interface BalanceHistoryRepo {

	void create(BalanceHistoryForm history);

}
