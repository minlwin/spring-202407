package com.jdc.spring.security.controller.advices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.spring.security.model.utils.DateTimes;

@ControllerAdvice
public class DateTimeFormatterAdvice {

	@Autowired
	private DateTimes dateTimes;
	
	@ModelAttribute(name = "dateTimes")
	public DateTimes getDateTimes() {
		return dateTimes;
	}
}
