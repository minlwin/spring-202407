package com.jdc.portal.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student/test")
public class StudentApi {

	@GetMapping
	List<String> demo() {
		return List.of("This is student api");
	}
}
