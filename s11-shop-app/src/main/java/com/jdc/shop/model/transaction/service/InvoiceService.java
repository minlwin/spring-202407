package com.jdc.shop.model.transaction.service;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.controller.output.SaleDetails;
import com.jdc.shop.controller.output.SaleInfo;
import com.jdc.shop.model.PageInfo;

public interface InvoiceService {

	default PageInfo<SaleInfo> search(String customerId, int page, int size) {
		return search(SaleSearch.withCustomerId(customerId), page, size);
	}

	PageInfo<SaleInfo> search(SaleSearch search, int page, int size);

	SaleDetails findById(String id);

	void cancel(String id, String reason);

}
