package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.spring.controller.input.SectionForm;
import com.jdc.spring.service.SectionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("sections/edit")
public class SectionEditController {

	private final SectionService service;
	
	@GetMapping
	String edit() {
		return "sections/edit";
	}
	
	@PostMapping
	String save(
			@Validated @ModelAttribute(name = "sectionForm") SectionForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "sections/edit";
		}
		
		service.save(form);
		
		return "redirect:/sections";
	}
	

	@ModelAttribute(name = "sectionForm")
	SectionForm sectionForm(
			@RequestParam(required = false) Integer id, 
			@RequestParam(required = false) Integer courseId) {
		return service.findForEdit(id, courseId);
	}
}
