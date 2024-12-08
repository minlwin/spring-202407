package com.jdc.spring.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.jdc.spring.security.config.WebMvcConfiguration;
import com.jdc.spring.security.config.WebSecurityConfiguration;

public class WebMvcApplicationLoader extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
			WebMvcConfiguration.class,
			WebSecurityConfiguration.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
