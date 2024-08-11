package com.jdc.spring.trx.service;

import com.jdc.spring.trx.dto.input.LimitValidationForm;

public interface LimitValidationService {

	void validate(LimitValidationForm limitForm);

}
