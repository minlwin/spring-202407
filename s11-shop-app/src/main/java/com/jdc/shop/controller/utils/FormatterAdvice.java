package com.jdc.shop.controller.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class FormatterAdvice {
	
	@Value("${spring.mvc.format.date}")
	private String dateFormat;
	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;

	@ModelAttribute(name = "formatter")
	public DateFormatter dateFormatter() {
		return new DateFormatter(dateFormat, dateTimeFormat);
	}
	
	public static class DateFormatter {
		
		private String dateFormat;
		private String dateTimeFormat;
		
		public DateFormatter(String dateFormat, String dateTimeFormat) {
			super();
			this.dateFormat = dateFormat;
			this.dateTimeFormat = dateTimeFormat;
		}

		public String date(LocalDate date) {
			
			if(null != date) {
				return date.format(DateTimeFormatter.ofPattern(dateFormat));
			}
			
			return null;
		}

		public String dateTime(LocalDateTime date) {
			
			if(null != date) {
				return date.format(DateTimeFormatter.ofPattern(dateTimeFormat));
			}

			return null;
		}
	}
}
