package com.jdc.portal.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student/test")
public class StudentApi {

	@GetMapping
	String demo() {
		return "This is student api";
	}
}
