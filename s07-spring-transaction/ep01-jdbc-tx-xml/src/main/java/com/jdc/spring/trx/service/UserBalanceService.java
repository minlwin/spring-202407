package com.jdc.spring.trx.service;

import java.util.List;

import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;

public interface UserBalanceService {

	List<UserBalanceInfo> search(UserBalanceSearch search);
}
