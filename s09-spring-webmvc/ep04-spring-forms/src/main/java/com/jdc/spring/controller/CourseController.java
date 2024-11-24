package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.controller.input.CourseSearch;
import com.jdc.spring.service.CourseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseController {

	private final CourseService service;
	
	@GetMapping
	String search(CourseSearch search, ModelMap model) {
		
		var list = service.search(search);
		model.put("list", list);
		
		return "courses/list";
	}
	
	@GetMapping("{id}")
	String showDetails(@PathVariable int id, ModelMap model) {
		
		var details = service.findById(id);
		model.put("details", details);
		
		return "courses/details";
	}
	
}
