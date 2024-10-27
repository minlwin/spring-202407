package com.jdc.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.web.model.repo.RegionRepo;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	private RegionRepo regionRepo;
	

	@GetMapping
	String index(ModelMap model) {
		model.put("list", regionRepo.findAll());
		return "index";
	}
}
