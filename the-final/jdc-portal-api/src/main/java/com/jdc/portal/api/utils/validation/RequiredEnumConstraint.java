package com.jdc.portal.api.utils.validation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidatorContext;

@Component
public class RequiredEnumConstraint extends RequiredBaseConstraint<Enum<?>>{
	
	public RequiredEnumConstraint(MessageSource messageSource) {
		super(messageSource);
	}
	
	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		
		if(null == value) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessage())
				.addConstraintViolation();
			return false;
		}
		
		return true;
	}


}
