package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.PurchaseSearch;
import com.jdc.shop.model.transaction.service.PurchaseAdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/purchase")
public class PurchaseController {
	
	private final PurchaseAdminService service;
	
	@GetMapping
	String search(ModelMap model, 
			PurchaseSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "5") int size) {
		model.put("list", service.search(form, page, size));
		return "purchase/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable String id, ModelMap model) {
		model.put("details", service.findById(id));
		return "purchase/details";
	}
}
