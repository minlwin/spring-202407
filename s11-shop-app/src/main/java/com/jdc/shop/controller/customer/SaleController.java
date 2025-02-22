package com.jdc.shop.controller.customer;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.model.account.service.CustomerReferenceService;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("customer/sale")
public class SaleController {
	
	private final InvoiceService invoiceService;
	private final CustomerReferenceService customerService;

	@GetMapping
	String search(
			SaleSearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10")  int size,
			ModelMap model) {
		
		model.put("detailsPath", "customer/sale");
		model.put("customerId", customerService.findIdByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.put("statuses", Status.values());
		model.put("result", invoiceService.search(search, page, size));
		
		return "invoice/list";
	}

	@GetMapping("{id}")
	String findDetails(@PathVariable String id, ModelMap model) {
		model.put("details", invoiceService.findById(id));
		model.put("cancelUrl", "/customer/sale/%s/cancel".formatted(id));
		return "invoice/details";
	}
	
	@PostMapping("{id}/cancel")
	String cancel(@PathVariable String id, @RequestParam String reason) {
		invoiceService.cancel(id, reason);
		return "redirect:/customer/sale/%s".formatted(id);
	}

}
