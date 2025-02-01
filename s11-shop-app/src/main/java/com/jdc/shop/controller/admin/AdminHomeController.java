package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("admin/home")
public record AdminHomeController() {

	@GetMapping
	public String home() {
		return "admin/home";
	}
}
