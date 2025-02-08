package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.shop.controller.input.CustomerSearch;
import com.jdc.shop.controller.output.SaleInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.account.service.CustomerReferenceService;
import com.jdc.shop.model.transaction.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/customer")
public class CustomerController {

	private final CustomerReferenceService customerService;
	private final InvoiceService invoiceService;
	
	@GetMapping
	String search(ModelMap model, CustomerSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		var pageInfo = customerService.search(search, page, size);
		model.put("result", pageInfo);
		return "customer/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable String id, ModelMap model) {
		model.put("details", customerService.findById(id));
		return "customer/details";
	}

	@ResponseBody
	@GetMapping("{id}/invoices")
	PageInfo<SaleInfo> findInvoices(@PathVariable String id, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return invoiceService.search(id, page, size);
	}
}
