package com.jdc.shop.model.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.input.PurchaseFormItem;
import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.PurchaseProduct;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseItemService {
	
	private final ProductService productService;

	@Transactional
	public void createItems(Purchase purchase, List<PurchaseFormItem> items) {
		
		for(var item : items) {
			
			var product = productService.createOrGetProduct(item);
			
			var entity = new PurchaseProduct();
			entity.setProduct(product);
			entity.setPurchase(purchase);
			
			
		}
	}

}
