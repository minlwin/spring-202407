package com.jdc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		var details = service.findById(id);
		model.put("details", details);
		return "sections/details";
	}

}
