package com.jdc.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.web.controller.dto.User;

@Controller
@RequestMapping("home")
public class HomeController {

	@GetMapping
	String index(ModelMap model) {
		
		model.put("message", "Hello From Spring MVC");
		model.put("user", new User("Aung Aung", "0918171716", "aung@gmail.com"));
		
		return "index";
	}
}
