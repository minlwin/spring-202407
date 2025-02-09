package com.jdc.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.input.ProductSearch;
import com.jdc.shop.model.master.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

	private final ProductService service;
	
	@GetMapping
	String index(ProductSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "12") int size,
			ModelMap model) {
		model.put("result", service.search(search, page, size));
		return "home";
	}
}
