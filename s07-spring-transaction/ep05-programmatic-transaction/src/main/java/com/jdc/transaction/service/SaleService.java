package com.jdc.transaction.service;

import com.jdc.transaction.service.model.SaleForm;
import com.jdc.transaction.service.model.SaleResult;

public interface SaleService {

	SaleResult checkOut(SaleForm form);
}
