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

import com.jdc.spring.controller.input.StudentForm;
import com.jdc.spring.model.entity.Student.Education;
import com.jdc.spring.service.StudentService;

@Controller
@RequestMapping("students/edit")
public class StudentEditController {
	
	@Autowired
	private StudentService service;

	@GetMapping
	String edit() {
		return "students/edit";
	}
	
	@PostMapping
	String save(@Validated @ModelAttribute(name = "studentForm") StudentForm form, 
			BindingResult result) {
		
		validateBusiness(form, result);
		
		if(result.hasErrors()) {
			return "students/edit";
		}
		
		service.save(form);
		
		return "redirect:/students";
	}
	
	@ModelAttribute(name = "studentForm")
	StudentForm studentForm(@RequestParam int id) {
		return service.findForEdit(id);
	}
	
	@ModelAttribute(name = "educations")
	Education[] educations() {
		return Education.values();
	}

	private void validateBusiness(StudentForm form, BindingResult result) {
		if(!service.findEmailById(form.getId()).equals(form.getEmail()) 
				&& service.findByEmail(form.getEmail())) {
			result.rejectValue("email", null, "Your email has been already used.");
		}
	}

}
