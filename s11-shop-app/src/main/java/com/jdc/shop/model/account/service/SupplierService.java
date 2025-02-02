package com.jdc.shop.model.account.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.controller.input.PurchaseForm.SupplierFormMode;
import com.jdc.shop.controller.input.SupplierSearch;
import com.jdc.shop.controller.output.SupplierInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.repo.SupplierRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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

	public SupplierInfo findById(int id) {
		return safeCall(supplierRepo.findById(id).map(SupplierInfo::from), "Supplier", "id", id);
	}

	public PageInfo<SupplierInfo> search(SupplierSearch search, int page, int size) {
		return supplierRepo.search(queryFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<SupplierInfo>> queryFunc(SupplierSearch search) {
		return cb -> {
			var cq = cb.createQuery(SupplierInfo.class);
			var root = cq.from(Supplier.class);
			
			SupplierInfo.select(cq, root);
			cq.where(search.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(SupplierSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Supplier.class);
			
			cq.select(cb.count(root));
			cq.where(search.where(cb, root));
			
			return cq;
		};	
	}

}
