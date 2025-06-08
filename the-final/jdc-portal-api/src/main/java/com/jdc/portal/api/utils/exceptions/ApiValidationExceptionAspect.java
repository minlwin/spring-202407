package com.jdc.portal.api.utils.exceptions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ApiValidationExceptionAspect {
	
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void apiMethods() {}

	@Before(value = "apiMethods() and args(.., bindingResult)", argNames = "bindingResult")
	public void checkValidationError(BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new ApiValidationException(bindingResult.getFieldErrors()
					.stream().map(e -> e.getDefaultMessage()).toList());
		}
	}
}
