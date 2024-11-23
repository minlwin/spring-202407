package com.jdc.spring.controller.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime>{
	
	private DateTimeFormatter formatter;
	
	public LocalDateTimeFormatter(String format) {
		formatter = DateTimeFormatter.ofPattern(format);
	}

	@Override
	public String print(LocalDateTime object, Locale locale) {
		
		if(null != object) {
			return object.format(formatter);
		}
		
		return null;
	}

	@Override
	public LocalDateTime parse(String text, Locale locale) throws ParseException {
		
		if(StringUtils.hasLength(text)) {
			return LocalDateTime.parse(text, formatter);
		}
		
		return null;
	}

}
