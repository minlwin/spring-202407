package com.jdc.shop.model.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.model.account.entity.Supplier;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Supplier getOrCreateSupplier(PurchaseForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
