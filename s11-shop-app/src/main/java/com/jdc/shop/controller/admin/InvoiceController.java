package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.model.transaction.service.InvioceService;

@Controller
@RequestMapping("admin/invoice")
public record InvoiceController(InvioceService service) {

	@GetMapping
	public String search(SaleSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return "invoice/list";
	}
	
	@GetMapping("{id}")
	public String findDetails(@PathVariable String id) {
		return "invoice/details";
	}
}
