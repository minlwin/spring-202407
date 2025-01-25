package com.jdc.shop.model.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.PurchaseFormItem;
import com.jdc.shop.model.master.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	@Transactional
	public Product createOrGetProduct(PurchaseFormItem item) {
		// TODO Auto-generated method stub
		return null;
	}

}
