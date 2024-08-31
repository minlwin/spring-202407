package com.jdc.spring.trx.service;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.input.LimitValidationForm;

public interface LimitValidationService {

	@Transactional(readOnly = true)
	void validate(LimitValidationForm limitForm);

}
