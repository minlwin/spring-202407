package com.jdc.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.spring.security.controller.input.SignUpForm;
import com.jdc.spring.security.model.service.MemberSignUpService;

@Controller
@RequestMapping("signup")
public class SignUpController {
	
	@Autowired
	private MemberSignUpService service;
	
	@GetMapping
	String signUp() {
		return "signup";
	}

	@PostMapping
	String signUp(
			@ModelAttribute(name = "signUpForm") @Validated SignUpForm form, 
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		service.signUp(form);
		
		attributes.addFlashAttribute("message", 
				"Hello %s, your request is acceped.".formatted(form.getName()));
		
		return "redirect:/";
	}
	
	@ModelAttribute(name = "signUpForm")
	SignUpForm signUpForm() {
		return new SignUpForm();
	}
}
