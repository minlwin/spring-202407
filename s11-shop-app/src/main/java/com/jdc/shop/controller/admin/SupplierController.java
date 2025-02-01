package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.shop.controller.input.SupplierSearch;
import com.jdc.shop.controller.output.ProductInfo;
import com.jdc.shop.controller.output.PurchaseInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.transaction.service.ProductService;
import com.jdc.shop.model.transaction.service.PurchaseService;
import com.jdc.shop.model.transaction.service.SupplierService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/supplier")
public class SupplierController {
	
	private final SupplierService supplierService;
	private final ProductService productService;
	private final PurchaseService purchaseService;

	@GetMapping
	String search(
			ModelMap model,
			SupplierSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		
		model.put("result", supplierService.search(search, page, size));
		
		return "supplier/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		model.put("details", supplierService.findById(id));
		return "supplier/detaisl";
	}
	
	@ResponseBody
	@GetMapping("{id}/product")
	PageInfo<ProductInfo> findSuppliedProducts(@PathVariable int id, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return productService.findBySupplier(id, page, size);
	}

	@ResponseBody
	@GetMapping("{id}/purchase")
	PageInfo<PurchaseInfo> findSuppliedPurchase(@PathVariable int id, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return purchaseService.findBySupplier(id, page, size);
	}
}
