package com.jdc.spring.trx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;
import com.jdc.spring.trx.repo.BalanceHistoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService{
	
	private final BalanceHistoryRepo historyRepo;

	@Override
	public List<UserBalanceInfo> search(UserBalanceSearch search) {
		return historyRepo.search(search);
	}

}
