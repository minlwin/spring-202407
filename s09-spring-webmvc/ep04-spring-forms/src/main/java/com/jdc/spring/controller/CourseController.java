package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.model.entity.Course.Level;

@Controller
@RequestMapping("courses")
public class CourseController {

	@GetMapping
	String search() {
		return "courses/list";
	}
	
	@GetMapping("edit")
	String edit() {
		return "courses/edit";
	}
	
	@ModelAttribute(name = "levels")
	Level[] levels() {
		return Level.values();
	}
}
