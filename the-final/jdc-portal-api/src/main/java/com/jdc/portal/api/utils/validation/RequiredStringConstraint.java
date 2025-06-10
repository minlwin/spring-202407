package com.jdc.portal.api.utils.validation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidatorContext;

@Component
public class RequiredStringConstraint extends RequiredBaseConstraint<String>{
	
	public RequiredStringConstraint(MessageSource messageSource) {
		super(messageSource);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(!StringUtils.hasLength(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessage())
				.addConstraintViolation();
			return false;
		}
		
		return true;
	}

}
