package com.jdc.spring.trx.service;

import org.springframework.stereotype.Service;

import com.jdc.spring.trx.dto.input.LimitValidationForm;
import com.jdc.spring.trx.dto.input.TransferForm;
import com.jdc.spring.trx.dto.output.TransferDetails;
import com.jdc.spring.trx.repo.AccountRepo;
import com.jdc.spring.trx.repo.TransactionTransferRepo;
import com.jdc.spring.trx.utils.BusinessException;
import com.jdc.spring.trx.utils.constants.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{
	
	private final TransactionTransferRepo transferRepo;
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
		
		// Create Transfer Transaction
		
		// Create Balance History for sender
		
		// Create Balance History for receiver
		
		// update Sender balance
		
		// update receiver balance
		
		// update transaction status
		
		var accountTo = accountRepo.findById(form.to())
				.orElseThrow(() -> new BusinessException("Invalid transfer to account id."));
		return 0;
	}

	@Override
	public TransferDetails findById(int id) {
		return transferRepo.findById(id);
	}

}
