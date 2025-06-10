package com.jdc.portal.api.utils.validation;

import java.time.temporal.Temporal;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidatorContext;

@Component
public class RequiredTemporalConstraint  extends RequiredBaseConstraint<Temporal>{

	public RequiredTemporalConstraint(MessageSource messageSource) {
		super(messageSource);
	}

	@Override
	public boolean isValid(Temporal value, ConstraintValidatorContext context) {

		if(null == value) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessage())
				.addConstraintViolation();
			return false;
		}
		
		return true;
	}

}
