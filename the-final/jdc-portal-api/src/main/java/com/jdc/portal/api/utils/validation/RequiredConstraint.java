package com.jdc.portal.api.utils.validation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequiredConstraint implements ConstraintValidator<Required, String>{
	
	private Required annotation;
	private final MessageSource messageSource;
	private static final String MESSAGE = "app.validation.required";
	
	@Override
	public void initialize(Required constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(!StringUtils.hasLength(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessage())
				.addConstraintViolation();
		}
		
		return true;
	}

	private String getMessage() {
		return messageSource.getMessage(
				MESSAGE, 
				new Object[] {annotation.name()}, 
				Locale.getDefault());
	}

}
