package com.jdc.spring.controller.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.spring.model.entity.Course.Level;

@ControllerAdvice
public class AppControllerAdvice {

	@ModelAttribute(name = "levels")
	Level[] levels() {
		return Level.values();
	}

}
