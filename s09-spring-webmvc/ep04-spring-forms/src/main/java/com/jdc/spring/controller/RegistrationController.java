package com.jdc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.controller.input.RegistrationSearch;
import com.jdc.spring.service.RegistrationService;

@Controller
@RequestMapping("registrations")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;

	@GetMapping
	String search(RegistrationSearch search, ModelMap model) {
		var list = service.search(search);
		model.put("list", list);
		return "registrations/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable String id, ModelMap model) {
		
		var details = service.findById(id);
		model.put("details", details);
		
		return "registrations/details";
	}

}
