package com.jdc.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppConfigLoader extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?> [] {
			RootConfiguration.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?> [] {
			MvcConfiguration.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
