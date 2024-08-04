package com.jdc.spring.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DeclareParentAspect {

	@DeclareParents(value = "com.jdc.spring.aop.service.*+", defaultImpl = TrackableImpl.class)
	public static Trackable tracker;
}
