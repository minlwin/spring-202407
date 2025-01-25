package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/supplier")
public class SupplierController {

	@GetMapping
	String search() {
		return "";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id) {
		return "";
	}
	
	@GetMapping("{id}/product")
	String findSuppliedProducts(@PathVariable int id) {
		return "";
	}

	@GetMapping("{id}/purchase")
	String findSuppliedPurchase(@PathVariable int id) {
		return "";
	}
}
