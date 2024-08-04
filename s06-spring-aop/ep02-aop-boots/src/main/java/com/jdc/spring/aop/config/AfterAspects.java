package com.jdc.spring.aop.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterAspects {

	@Pointcut("execution(int com.jdc..controller.Controller1.testing(String))")
	public void testingMethod() {}
	
	@After(value = "testingMethod()")
	public void after() {
		System.out.println("""
				-----------------
				After Advice
				-----------------
				""");
	}
	
	@AfterReturning(value = "testingMethod()", returning = "result")
	public void afterReturning(int result) {
		System.out.println("""
				-----------------
				After Returning Advice
				-----------------
				""");
	}
	
	@AfterThrowing(value = "testingMethod()", throwing = "exception")
	public void afterThrowing(Throwable exception) {
		System.out.println("""
				-----------------
				After Throwing Advice
				-----------------
				""");
		
	}
}
