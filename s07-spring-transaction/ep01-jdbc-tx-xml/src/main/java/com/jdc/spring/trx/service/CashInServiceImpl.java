package com.jdc.spring.trx.service;

import org.springframework.stereotype.Service;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;
import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.input.LimitValidationForm;
import com.jdc.spring.trx.dto.input.TransactionBaseForm;
import com.jdc.spring.trx.dto.output.CashInDetails;
import com.jdc.spring.trx.repo.AccountRepo;
import com.jdc.spring.trx.repo.BalanceHistoryRepo;
import com.jdc.spring.trx.repo.TransactionBaseRepo;
import com.jdc.spring.trx.repo.TransactionCashInRepo;
import com.jdc.spring.trx.utils.BusinessException;
import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CashInServiceImpl implements CashInService {

	private final LimitValidationService limitValidationService;
	
	private final TransactionCashInRepo cashInRepo;
	private final AccountRepo accountRepo;
	private final TransactionBaseRepo baseRepo;
	private final BalanceHistoryRepo historyRepo;
	
	@Override
	public CashInDetails findById(int id) {
		return cashInRepo.findById(id);
	}

	@Override
	public int cashIn(CashInForm form) {
		
		// Get Accounts
		var account = accountRepo.findById(form.account())
				.orElseThrow(() -> new BusinessException("Invalid transfer from account id."));
		
		// Check Limit
		var limitForm = new LimitValidationForm(
				account.loginId(), 
				account.level(), 
				TransactionType.CashIn, 
				account.amount(), 
				form.amount());
		
		limitValidationService.validate(limitForm);
		
		// Initiate Transaction 
		var trxId = baseRepo.create(new TransactionBaseForm(
				TransactionType.CashIn, 
				LedgerType.Credit, 
				form.account(), 
				account.amount(),
				form.amount(), form.particular()));
		
		// Create Cash In Transaction
		cashInRepo.create(trxId, form);
		
		// Create Balance History
		var history = new BalanceHistoryForm(trxId, 
				account.loginId(), 
				account.amount(), 
				form.amount(), 
				LedgerType.Credit, 
				form.particular());
		
		historyRepo.create(history);

		// Update Balance to account
		var balance = account.amount() + form.amount();
		accountRepo.updateBalance(account.loginId(), balance);
		
		// Update Transaction Status
		baseRepo.updateStatus(trxId, TransactionStatus.Success);
		
		return trxId;
	}

}
