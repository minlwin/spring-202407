package com.jdc.shop.model.account.service;

import java.util.UUID;

import com.jdc.shop.controller.input.CustomerSearch;
import com.jdc.shop.controller.output.CustomerDetails;
import com.jdc.shop.controller.output.CustomerInfo;
import com.jdc.shop.model.PageInfo;

public interface CustomerReferenceService {

	PageInfo<CustomerInfo> search(CustomerSearch search, int page, int size);

	CustomerDetails findById(String id);
	
	UUID findIdByUsername(String username);

}
