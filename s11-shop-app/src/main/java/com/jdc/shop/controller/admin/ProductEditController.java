package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/product/{id}/edit")
public class ProductEditController {

	@GetMapping
	String edit() {
		return "";
	}
	
	@PostMapping
	String save() {
		return "";
	}	
}
