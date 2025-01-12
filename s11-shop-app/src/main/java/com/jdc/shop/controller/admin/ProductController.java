package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.shop.controller.input.ProductSearch;

@Controller
@RequestMapping("admin/product")
public class ProductController {

	@GetMapping
	String search(ProductSearch form, ModelMap model) {
		return "product/list";
	}
	
	@GetMapping("{id}/details")
	String showDetails() {
		return "product/list";
	}
}
