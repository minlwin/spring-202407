package com.jdc.shop.model.transaction.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.transaction.PurchaseIdGenerator;
import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.Purchase.Status;
import com.jdc.shop.model.transaction.entity.PurchasePk;
import com.jdc.shop.model.transaction.repo.PurchaseRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseAdminService {
	
	private final SupplierService supplierService;
	private final PurchaseItemService itemService;
	private final PurchaseIdGenerator idGenerator;
	private final PurchaseRepo purchaseRepo;
	
	@Transactional
	public PurchasePk save(PurchaseForm form) {
		
		// Create or get Supplier
		var supplier = supplierService.getOrCreateSupplier(form);
		
		// Create Purchase 
		var purchase = initiatePurchase(supplier, form);
		
		try {
			// Create Purchase Items
			itemService.createItems(purchase, form.getItems());
			
			// Update to Success
			purchase.setStatus(Status.Success);
		
		} catch (RuntimeException e) {
			// Update for Error
			purchase.setStatus(Status.Error);
			purchase.setRemark(e.getMessage());
		}
		return purchase.getId();
	}


	private Purchase initiatePurchase(Supplier supplier, PurchaseForm form) {
		
		var entity = new Purchase();
		entity.setId(idGenerator.next(LocalDate.now()));
		entity.setSupplier(supplier);
		entity.setStatus(Status.Initiate);
		
		return purchaseRepo.saveAndFlush(entity);
	}

}
