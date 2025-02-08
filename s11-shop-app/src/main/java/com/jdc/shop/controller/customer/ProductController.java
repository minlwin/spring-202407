package com.jdc.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.ProductSearch;
import com.jdc.shop.model.master.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("customer/product")
public class ProductController {
	
	private final ProductService service;

	@GetMapping
	String search(
			@RequestParam String keyword,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10")  int size,
			ModelMap model) {
		
		model.put("result", service.search(ProductSearch.withKeyword(keyword), page, size));
		return "customer/product/list";
	}
	
	@GetMapping("{id}")
	String findDetails(@PathVariable int id, ModelMap model) {
		model.put("details", service.findById(id));
		return "customer/product/details";
	}
	
}
