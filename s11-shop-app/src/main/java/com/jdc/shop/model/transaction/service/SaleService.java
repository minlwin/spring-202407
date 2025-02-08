package com.jdc.shop.model.transaction.service;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.controller.output.SaleDetails;
import com.jdc.shop.controller.output.SaleInfo;
import com.jdc.shop.model.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService implements InvoiceService{@Override
	
	public PageInfo<SaleInfo> search(SaleSearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleDetails findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
