package com.jdc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.spring.controller.input.RegistrationForm;
import com.jdc.spring.service.RegistrationService;

@Controller
@RequestMapping("registrations/edit")
public class RegistrationEditController {
	
	@Autowired
	private RegistrationService service;

	@GetMapping
	String edit() {
		return "registrations/edit";
	}
	
	@PostMapping
	String save(@Validated @ModelAttribute("registForm") RegistrationForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "registrations/edit";
		}
		
		service.save(form);
		
		return "redirect:/registrations";
	}
	
	@ModelAttribute("registForm")
	RegistrationForm registForm(
			@RequestParam(required = false) String id,
			@RequestParam(required = false) Integer sectionId) {
		return service.findForEdit(id, sectionId);
	}
}
