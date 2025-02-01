package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.shop.model.transaction.service.InvioceService;

@Controller
@RequestMapping("admin/invoice")
public record InvoiceController(InvioceService service) {

	@GetMapping
	public String search() {
		return "invoice/list";
	}
	
	@GetMapping("id")
	public String findDetails(String id) {
		return "invoice/details";
	}
}
