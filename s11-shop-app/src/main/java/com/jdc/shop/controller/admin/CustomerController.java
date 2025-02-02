package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.CustomerSearch;
import com.jdc.shop.model.account.service.CustomerReferenceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/customer")
public class CustomerController {

	private final CustomerReferenceService service;
	
	@GetMapping
	String search(ModelMap model, CustomerSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		var pageInfo = service.search(search, page, size);
		model.put("result", pageInfo);
		return "customer/list";
	}
}
