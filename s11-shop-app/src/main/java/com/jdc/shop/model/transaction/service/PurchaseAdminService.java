package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.controller.input.PurchaseSearch;
import com.jdc.shop.controller.output.PurchaseDetails;
import com.jdc.shop.controller.output.PurchaseInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.transaction.PurchaseIdGenerator;
import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.Purchase.Status;
import com.jdc.shop.model.transaction.entity.PurchasePk;
import com.jdc.shop.model.transaction.entity.Purchase_;
import com.jdc.shop.model.transaction.repo.PurchaseRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
		
		} catch (Exception e) {
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

	public PurchaseDetails findById(String id) {
		return safeCall(
				purchaseRepo.findById(PurchasePk.parse(id)).map(PurchaseDetails::from), 
				"Purchase", "code", id);
	}


	public PageInfo<PurchaseInfo> search(PurchaseSearch form, int page, int size) {
		return purchaseRepo.search(queryFunc(form), countFunc(form), page, size);
	}


	private Function<CriteriaBuilder, CriteriaQuery<PurchaseInfo>> queryFunc(PurchaseSearch form) {
		return cb -> {
			var cq = cb.createQuery(PurchaseInfo.class);
			var root = cq.from(Purchase.class);
			var supplier = root.join(Purchase_.supplier);
			var items = root.join(Purchase_.products, JoinType.LEFT);
			
			PurchaseInfo.select(cq, cb, root, supplier, items);
			cq.where(form.where(cb, root, supplier));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(PurchaseSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Purchase.class);
			var supplier = root.join(Purchase_.supplier);
			cq.select(cb.count(root));
			cq.where(form.where(cb, root, supplier));
			return cq;
		};
	}

}
