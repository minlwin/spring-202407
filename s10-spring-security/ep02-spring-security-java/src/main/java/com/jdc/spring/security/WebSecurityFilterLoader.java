package com.jdc.spring.security;

import java.util.EnumSet;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import jakarta.servlet.DispatcherType;

public class WebSecurityFilterLoader extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC);
	}
}
