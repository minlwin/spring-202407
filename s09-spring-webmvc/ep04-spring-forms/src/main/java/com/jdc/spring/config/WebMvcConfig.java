package com.jdc.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jdc.spring.controller.formatter.LocalDateTimeFormatter;
import com.jdc.spring.interceptor.HomeViewInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(homeViewInterceptor())
			.addPathPatterns("/");
	}
	
	@Bean
	HomeViewInterceptor homeViewInterceptor() {
		return new HomeViewInterceptor();
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new LocalDateTimeFormatter(dateTimeFormat));
	}
}
