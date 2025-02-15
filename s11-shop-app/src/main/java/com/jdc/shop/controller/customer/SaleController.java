package com.jdc.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.model.transaction.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("customer/sale")
public class SaleController {
	
	private final InvoiceService service;

	@GetMapping
	String search(
			SaleSearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10")  int size,
			ModelMap model) {
		model.put("result", service.search(search, page, size));
		return "invoice/list";
	}

	@GetMapping("{id}")
	String findDetails(@PathVariable String id, ModelMap model) {
		model.put("details", service.findById(id));
		return "invoice/details";
	}

}
