package com.jdc.transaction.service;

import com.jdc.transaction.exceptions.BusinessException;
import com.jdc.transaction.repo.MemberRepo;
import com.jdc.transaction.service.model.SaleForm;

public abstract class AbstractSaleService implements SaleService {

	protected abstract MemberRepo memberRepo();
	
	protected void validate(SaleForm form) {

		if (null == form) {
			throw new BusinessException("Sale form must not be null.");
		}

		if (memberRepo().countById(form.memberId()) == 0) {
			throw new BusinessException("Invalid member id.");
		}

		if (null == form.items() || form.items().isEmpty()) {
			throw new BusinessException("Please select items.");
		}
	}
}
