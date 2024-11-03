package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.controller.input.CourseForm;
import com.jdc.spring.controller.validators.CourseFormValidator;
import com.jdc.spring.model.entity.Course.Level;

@Controller
@RequestMapping("courses")
public class CourseController {

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new CourseFormValidator());
	}

	@GetMapping
	String search() {
		return "courses/list";
	}
	
	@GetMapping("edit")
	String edit() {
		return "courses/edit";
	}
	
	@PostMapping
	String save(@Validated @ModelAttribute CourseForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "courses/edit";
		}
		
		return "redirect:/courses";
	}
	
	@ModelAttribute(name = "form")
	CourseForm form() {
		return new CourseForm();
	}
	
	@ModelAttribute(name = "levels")
	Level[] levels() {
		return Level.values();
	}
}
