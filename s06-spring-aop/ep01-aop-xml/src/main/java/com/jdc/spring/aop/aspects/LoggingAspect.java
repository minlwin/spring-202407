package com.jdc.spring.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;

import com.jdc.spring.aop.model.RegistrationForm;

public class LoggingAspect {

	public void beforeAdvice(RegistrationForm form) {
		
		try {
			System.out.println("Before Executing Method");
			System.out.println(form);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void afterReturningAdvice(Object result) {
		System.out.println("Checking Result");
		System.out.println(result);
	}
	
	public Object arroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		try {
			// Before
			System.out.printf("Find By Id method with id : %s%n", joinPoint.getArgs()[0]);
			var result = joinPoint.proceed();
			
			// After Returning
			
			return result;
		} catch (Throwable e) {
			// After Throwing
			
			throw e;
		} finally {
			// After
		}
		
	}
}
