package com.jdc.spring.trx.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.spring.trx.model.AccountException;
import com.jdc.spring.trx.model.dto.Account;
import com.jdc.spring.trx.model.repo.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private TrxHistoryService historyService;

	@Override
	public void transfer(int from, int to, int amount) {
		
		var historyId = historyService.createHistory("Transfer from %d to %d.".formatted(from, to), amount, "Initiate");
		
		var accountFrom = repo.findById(from)
				.orElseThrow(() -> new AccountException(historyId, "There is no account with id %s.".formatted(from)));
		
		if(accountFrom.amount() < amount) {
			throw new AccountException(historyId, "There is no enought amount to transfer.");
		}
		
		var accountTo = repo.findById(to)
				.orElseThrow(() -> new AccountException(historyId, "There is no account with id %s.".formatted(to)));
		
		var fromAmount = accountFrom.amount() - amount;
		var toAmount = accountTo.amount() + amount;
		
		
		repo.update(from, fromAmount);
		repo.update(to, toAmount);
		
		historyService.updateStatus(historyId, "Success");

	}

	@Override
	public List<Account> add(int amount) {
		
		var accounts = repo.findAll();
		
		for(var account : accounts) {
			var addAmount = account.amount() + amount;
			repo.update(account.id(), addAmount);
		}
		
		return repo.findAll();
	}

	@Override
	public List<Account> findAll() {
		return repo.findAll();
	}

}
