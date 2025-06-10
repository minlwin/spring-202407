package com.jdc.portal.api.utils.validation;

import java.util.Locale;

import org.springframework.context.MessageSource;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RequiredBaseConstraint<T>  implements ConstraintValidator<Required, T>{

	private Required annotation;
	private final MessageSource messageSource;
	private static final String MESSAGE = "app.validation.required";

	@Override
	public void initialize(Required constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	protected String getMessage() {
		return messageSource.getMessage(
				MESSAGE, 
				new Object[] {annotation.name()}, 
				Locale.getDefault());
	}
}
