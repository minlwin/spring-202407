package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.ProductSearch;
import com.jdc.shop.model.transaction.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/product")
public class ProductController {
	
	private final ProductService service;

	@GetMapping
	String search(
			ProductSearch form, 
			ModelMap model,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		model.put("list", service.search(form, page, size));
		return "product/list";
	}
	
	@GetMapping("{id}/details")
	String showDetails(@PathVariable int id, ModelMap model) {
		model.put("dto", service.findById(id));
		return "product/list";
	}
}
