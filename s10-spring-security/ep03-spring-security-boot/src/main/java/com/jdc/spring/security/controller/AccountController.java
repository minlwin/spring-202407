package com.jdc.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.spring.security.controller.input.AccountSearch;
import com.jdc.spring.security.model.service.AccountManagementService;

@Controller
@RequestMapping("admin/account")
public class AccountController {
	
	@Autowired
	private AccountManagementService service;

	@GetMapping
	String search(AccountSearch search, ModelMap model) {
		model.put("list", service.search(search));
		return "account/list";
	}
	
	@PostMapping("status")
	@PreAuthorize("hasAuthority('Admin')")
	String updateStatus(@RequestParam String id) {
		service.updateStatus(id);
		return "redirect:/admin/account";
	}
}
