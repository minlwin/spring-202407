package com.jdc.spring.security.model.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DateTimes {

	@Value("${spring.mvc.format.date-time}")
	private String format;
	
	private DateTimeFormatter formatter;
	
	@PostConstruct
	public void postConstruct() {
		formatter = DateTimeFormatter.ofPattern(format);
	}
	
	public String format(LocalDateTime dateTime) {
		if(null != dateTime) {
			return dateTime.format(formatter);
		}
		
		return null;
	}
}
