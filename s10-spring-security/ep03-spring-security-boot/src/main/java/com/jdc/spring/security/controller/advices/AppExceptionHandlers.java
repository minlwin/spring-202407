package com.jdc.spring.security.controller.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.jdc.spring.security.model.AppBusinessException;

@ControllerAdvice
public class AppExceptionHandlers {

	@ExceptionHandler
	ModelAndView handle(AppBusinessException e) {
		var mv = new ModelAndView("errors");
		mv.getModel().put("message", e.getMessage());
		return mv;
	}

}
