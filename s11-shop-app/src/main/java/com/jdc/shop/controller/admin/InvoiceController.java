package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/invoice")
public class InvoiceController {
	
	private final InvoiceService service;

	@GetMapping
	public String search(SaleSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		model.put("detailsPath", "admin/invoice");
		model.put("statuses", Status.values());
		model.put("result", service.search(search, page, size));
		return "invoice/list";
	}
	
	@GetMapping("{id}")
	public String findDetails(@PathVariable String id, ModelMap model) {
		model.put("details", service.findById(id));
		model.put("cancelUrl", "/admin/invoice/%s/cancel".formatted(id));
		return "invoice/details";
	}
	
	@PostMapping("{id}/cancel")
	String cancel(@PathVariable String id, @RequestParam String reason) {
		service.cancel(id, reason);
		return "redirect:/admin/invoice/%s".formatted(id);
	}	
}
