package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.spring.controller.input.CourseForm;
import com.jdc.spring.service.CourseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("courses/edit")
public class CourseEditController {

	private final CourseService service;

	@GetMapping
	String edit() {
		return "courses/edit";
	}
	
	@PostMapping
	String save(@Validated @ModelAttribute(name = "courseForm") CourseForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "courses/edit";
		}
		
		service.save(form);
		
		return "redirect:/courses";
	}
	
	@ModelAttribute(name = "courseForm")
	CourseForm form(@RequestParam(required = false) Integer id) {
		
		if(null != id) {
			return service.findForEdit(id);
		}
		
		return new CourseForm();
	}
}
