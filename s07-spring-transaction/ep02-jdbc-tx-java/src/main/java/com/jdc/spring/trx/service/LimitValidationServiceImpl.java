package com.jdc.spring.trx.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.jdc.spring.trx.dto.input.LimitValidationForm;
import com.jdc.spring.trx.repo.LimitSettingRepo;
import com.jdc.spring.trx.repo.TransactionBaseRepo;
import com.jdc.spring.trx.utils.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LimitValidationServiceImpl implements LimitValidationService {

	private final LimitSettingRepo settingRepo;
	private final TransactionBaseRepo baseRepo;
	
	@Override
	public void validate(LimitValidationForm form) {
		
		// Get Limit Setting
		var setting = settingRepo.findById(form.level(), form.type());
		
		if(setting.isPresent()) {
			
			var limits = setting.get();
			// Check Min Limit
			if(form.trxAmount() < limits.minLimit()) {
				throw new BusinessException("Transaction is under minimum limit %d.".formatted(limits.minLimit()));
			}
			
			// Check Max Limit
			if(form.trxAmount() > limits.maxLimit()) {
				throw new BusinessException("Transaction is exeed maximum limit %d.".formatted(limits.maxLimit()));
			}
			
			// Get Daily total amount
			var dailyTotal = baseRepo.findTotalAmount(form.userId(), form.type(), LocalDate.now());
			
			// Check Daily Limit
			var total = dailyTotal.intValue() + form.trxAmount();
			if(total > limits.dailyLimit()) {
				throw new BusinessException("Transaction is exeed daily maximum limit %d.".formatted(limits.dailyLimit()));
			}
			
		}		
	}

}
