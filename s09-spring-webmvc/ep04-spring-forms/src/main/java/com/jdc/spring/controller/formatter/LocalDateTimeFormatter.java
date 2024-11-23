package com.jdc.spring.controller.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {
	
	private DateTimeFormatter formatter;
	
	public LocalDateTimeFormatter(String format) {
		formatter = DateTimeFormatter.ofPattern(format);
	}
	
	public String format(LocalDateTime object) {
		
		if(null != object) {
			return object.format(formatter);
		}
		
		return null;
	}

}
