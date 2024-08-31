package com.jdc.spring.trx.service;

import org.springframework.stereotype.Service;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;
import com.jdc.spring.trx.dto.input.LimitValidationForm;
import com.jdc.spring.trx.dto.input.TransactionBaseForm;
import com.jdc.spring.trx.dto.input.TransferForm;
import com.jdc.spring.trx.dto.output.TransferDetails;
import com.jdc.spring.trx.repo.AccountRepo;
import com.jdc.spring.trx.repo.BalanceHistoryRepo;
import com.jdc.spring.trx.repo.TransactionBaseRepo;
import com.jdc.spring.trx.repo.TransactionTransferRepo;
import com.jdc.spring.trx.utils.BusinessException;
import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{
	
	private final TransactionBaseRepo baseRepo;
	private final TransactionTransferRepo transferRepo;
	private final BalanceHistoryRepo historyRepo;

	private final AccountRepo accountRepo;
	private final LimitValidationService limitService;


	@Override
	public int transfer(TransferForm form) {
		
		var accountFrom = accountRepo.findById(form.from())
				.orElseThrow(() -> new BusinessException("Invalid transfer from account id."));
		
		limitService.validate(LimitValidationForm.builder()
				.userId(accountFrom.loginId())
				.level(accountFrom.level())
				.type(TransactionType.Transfer)
				.trxAmount(form.amount())
				.userBalance(accountFrom.amount())
				.build());
		
		// Initiate Transaction
		var trxId = baseRepo.create(TransactionBaseForm.builder()
				.account(accountFrom.loginId())
				.ledger(LedgerType.Debit)
				.type(TransactionType.Transfer)
				.beforeAmount(accountFrom.amount())
				.amount(form.amount())
				.particular(form.particular())
				.build());
		
		// Create Transfer Transaction
		var accountTo = accountRepo.findById(form.to())
				.orElseThrow(() -> new BusinessException("Invalid transfer to account id."));
		
		transferRepo.create(trxId, accountTo.loginId(), accountTo.amount());
		
		// Create Balance History for sender
		historyRepo.create(BalanceHistoryForm.builder()
				.trxId(trxId)
				.accountId(accountFrom.loginId())
				.beforeAmount(accountFrom.amount())
				.trxAmount(form.amount())
				.ledger(LedgerType.Debit)
				.particular(form.particular())
				.build());
		
		// Create Balance History for receiver
		historyRepo.create(BalanceHistoryForm.builder()
				.trxId(trxId)
				.accountId(accountTo.loginId())
				.beforeAmount(accountTo.amount())
				.trxAmount(form.amount())
				.ledger(LedgerType.Credit)
				.particular(form.particular())
				.build());
		
		// update Sender balance
		accountRepo.updateBalance(accountFrom.loginId(), accountFrom.amount() - form.amount());
		
		// update receiver balance
		accountRepo.updateBalance(accountTo.loginId(), accountTo.amount() + form.amount());
		
		// update transaction status
		baseRepo.updateStatus(trxId, TransactionStatus.Success);
		
		return trxId;
	}

	@Override
	public TransferDetails findById(int id) {
		return transferRepo.findById(id);
	}

}
