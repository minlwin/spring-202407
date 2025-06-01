package com.jdc.portal.api.utils.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = RequiredConstraint.class)
public @interface Required {

	String message() default "{app.validation.required}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String name();
}
