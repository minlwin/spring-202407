package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.controller.input.PurchaseForm.SupplierFormMode;
import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.repo.SupplierRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
	
	private final SupplierRepo supplierRepo;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Supplier getOrCreateSupplier(PurchaseForm form) {
		
		if(form.getMode() == SupplierFormMode.Search) {
			// Find Supplier by code
			return safeCall(supplierRepo.findById(Integer.parseInt(form.getSupplierCode())), 
					"supplier", "code", form.getSupplierCode());
		}
		
		var entity = new Supplier();
		entity.setName(form.getSupplierName());
		entity.setPhone(form.getPhone());
		entity.setEmail(form.getEmail());
		entity.setShopName(form.getShopName());
		entity.setAddress(form.getAddress());
		
		return supplierRepo.saveAndFlush(entity);
	}

}
