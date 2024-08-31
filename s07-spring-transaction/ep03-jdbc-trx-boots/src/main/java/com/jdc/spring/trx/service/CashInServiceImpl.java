package com.jdc.spring.trx.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
import com.jdc.spring.trx.utils.ValidationException;
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
		return cashInRepo.findById(id)
				.orElseThrow(() -> new BusinessException("Invalid transaction id."));
	}

	@Override
	public int cashIn(CashInForm form) {
		
		validate(form);
		
		// Get Accounts
		var account = accountRepo.findById(form.account())
				.orElseThrow(() -> new BusinessException("Invalid cash in account id."));
		
		// Check Limit
		limitValidationService.validate(LimitValidationForm.builder()
				.userId(account.loginId())
				.level(account.level())
				.type(TransactionType.CashIn)
				.userBalance(account.amount())
				.trxAmount(form.amount())
				.build());
		
		// Initiate Transaction 
		var trxId = baseRepo.create(TransactionBaseForm.builder()
				.type(TransactionType.CashIn)
				.ledger(LedgerType.Credit)
				.account(account.loginId())
				.beforeAmount(account.amount())
				.amount(form.amount())
				.particular(form.particular())
				.build());

		// Create Cash In Transaction
		cashInRepo.create(trxId, form);
		
		// Create Balance History
		historyRepo.create(BalanceHistoryForm.builder()
				.accountId(account.loginId())
				.beforeAmount(account.amount())
				.trxAmount(form.amount())
				.ledger(LedgerType.Credit)
				.particular(form.particular())
				.build());

		// Update Balance to account
		var balance = account.amount() + form.amount();
		accountRepo.updateBalance(account.loginId(), balance);
		
		// Update Transaction Status
		baseRepo.updateStatus(trxId, TransactionStatus.Success);
		
		return trxId;
	}

	private void validate(CashInForm form) {
		if(!StringUtils.hasLength(form.account())) {
			throw new ValidationException("Please enter login user id.");
		}
		
		if(!StringUtils.hasLength(form.cashInFrom())) {
			throw new ValidationException("Please select cash in from.");
		}
		
		if(null == form.amount()) {
			throw new ValidationException("Please enter cash in amount.");
		}
	}

}
