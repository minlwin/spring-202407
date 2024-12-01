package com.jdc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.controller.input.StudentSearch;
import com.jdc.spring.model.entity.Student.Education;
import com.jdc.spring.service.StudentService;

@Controller
@RequestMapping("students")
public class StudentController {
	
	@Autowired
	private StudentService service;

	@GetMapping
	String search(StudentSearch search, ModelMap model) {
		
		var list = service.search(search);
		model.put("list", list);
		
		return "students/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		model.put("details", service.findById(id));
		return "students/details";
	}
	
	@ModelAttribute(name = "educations")
	Education[] educations() {
		return Education.values();
	}

}
