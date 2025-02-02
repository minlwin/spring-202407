package com.jdc.shop.model.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.shop.controller.input.PurchaseFormItem;
import com.jdc.shop.model.master.service.ProductService;
import com.jdc.shop.model.transaction.entity.Purchase;
import com.jdc.shop.model.transaction.entity.PurchaseProduct;
import com.jdc.shop.model.transaction.repo.PurchaseProductRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseItemService {
	
	private final ProductService productService;
	private final PurchaseProductRepo purchaseProductRepo;

	@Transactional
	public void createItems(Purchase purchase, List<PurchaseFormItem> items) {
		
		for(var item : items) {
			
			var product = productService.createOrGetProduct(item);
			
			var entity = new PurchaseProduct();
			entity.setProduct(product);
			entity.setPurchase(purchase);
			
			entity.setBuyPrice(item.getBuyPrice());
			entity.setSellPrice(item.getSellPrice());

			entity.setBeforeStock(product.getStock().getStock());
			entity.setQuantity(item.getQuantity());
			
			purchaseProductRepo.saveAndFlush(entity);
			
			product.setSalePrice(item.getSellPrice());
			var stock = product.getStock();
			stock.setStock(entity.getQuantity() + entity.getBeforeStock());
		}
	}

}
