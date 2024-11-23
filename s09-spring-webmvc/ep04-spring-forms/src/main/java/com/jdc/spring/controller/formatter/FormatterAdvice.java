package com.jdc.spring.controller.formatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class FormatterAdvice {

	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;

	@ModelAttribute(name = "localDateTimes")
	LocalDateTimeFormatter localDateTimeFormatter() {
		return new LocalDateTimeFormatter(dateTimeFormat);
	}
}
