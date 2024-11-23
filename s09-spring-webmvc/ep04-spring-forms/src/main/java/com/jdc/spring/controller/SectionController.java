package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.spring.controller.input.SectionForm;
import com.jdc.spring.controller.input.SectionSearch;
import com.jdc.spring.service.SectionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("sections")
public class SectionController {
	
	private final SectionService service;

	@GetMapping
	String search(SectionSearch search, ModelMap model) {
		var list = service.search(search);
		model.put("list", list);
		return "sections/list";
	}
	
	@GetMapping("edit")
	String edit() {
		return "sections/edit";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		var details = service.findById(id);
		model.put("details", details);
		return "sections/details";
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
