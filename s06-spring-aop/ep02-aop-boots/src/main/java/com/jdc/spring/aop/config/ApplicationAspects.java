package com.jdc.spring.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ApplicationAspects {

	@Pointcut(value = "execution(* *(*))")
	public void methodWithOneArgument() {}
	
	@Pointcut(value = "within(com.jdc.spring.aop.service.*)")
	public void serviceClasses() {}
	
	@Before(value = "serviceClasses() && methodWithOneArgument() && args(value)", argNames = "value")
	public void beforeService(Object value) {
		
		System.out.println("""
				-------------------------->
				Advice from Before : %s
				-------------------------->
				""".formatted(value));
	}
}
