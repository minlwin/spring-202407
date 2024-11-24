package com.jdc.spring.controller.advices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class FormatterAdvice {

	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;

	@ModelAttribute(name = "localDateTimes")
	LocalDateTimeFormatter localDateTimeFormatter() {
		return new LocalDateTimeFormatter(dateTimeFormat);
	}
}
