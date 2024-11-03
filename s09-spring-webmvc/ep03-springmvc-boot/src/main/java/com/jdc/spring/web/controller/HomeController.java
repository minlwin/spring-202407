package com.jdc.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.web.controller.input.RegionSearch;
import com.jdc.spring.web.service.RegionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("home")
public class HomeController {

	@Autowired
	private RegionService service;
	
	@GetMapping
	String index(ModelMap model, RegionSearch search) {
		
		log.info("Region  : {}", search.region());
		log.info("Name    : {}", search.name());
		log.info("Capital : {}", search.capital());
	
		model.put("list", service.search(search));
		return "home";
	}
	
	@ModelAttribute("regions")
	List<String> regions() {
		return List.of("South", "East", "North", "West", "Central");
	}
}
