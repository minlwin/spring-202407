package com.jdc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.model.repo.RegionRepo;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	private RegionRepo regionRepo;
	
	
	@GetMapping
	String index(Model model) {
		model.addAttribute("list", regionRepo.findAll());
		return "index";
	}
	
	@GetMapping("{id}")
	String findByIdWithPath(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("dto", regionRepo.findById(id));
		return "details";
	}
		
}
