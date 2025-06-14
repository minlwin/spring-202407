package com.jdc.portal.api.utils.exceptions;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jdc.portal.api.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Aspect
@Configuration
@RequiredArgsConstructor
public class EmployeeInitializationRequiredAspect {
	
	private final AccountRepo accountRepo;

	@Pointcut("within(com.jdc.portal.api.controller.office.*)")
	public void officeApi() {}
	
	@Pointcut("within(com.jdc.portal.api.controller.teacher.*)")
	public void teacherApi() {}
	
	@Before("officeApi() or teacherApi()")
	public void check(JoinPoint joinPoint) {
		
		if(joinPoint.getSignature() instanceof MethodSignature signature) {
			Method method = signature.getMethod();
			var annotation = method.getAnnotation(EmployeeInitializationRequired.class);
			
			if(null != annotation && !annotation.value()) {
				return;
			}
			
		}
		
		var username = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		
		var account = accountRepo.findById(username).get();
		
		if(!account.isChangedPass()) {
			throw new ApiEmploeeNotChangePasswordException();
		}
	}
}
