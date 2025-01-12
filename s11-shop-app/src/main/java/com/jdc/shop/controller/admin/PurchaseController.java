package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.PurchaseSearch;

@Controller
@RequestMapping("admin/purchase")
public class PurchaseController {

	
	@GetMapping
	String search(ModelMap model, PurchaseSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "5") int size) {
		return "purchase/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable String id, ModelMap model) {
		return "purchase/details";
	}
}
