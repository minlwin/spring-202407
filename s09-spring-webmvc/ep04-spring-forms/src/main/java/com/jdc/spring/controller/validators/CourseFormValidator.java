package com.jdc.spring.controller.validators;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jdc.spring.controller.input.CourseForm;

public class CourseFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CourseForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof CourseForm form) {
			if(null == form.getLevel()) {
				errors.rejectValue("level", "course.level.required", "Please select course level.");
			}
			
			if(!StringUtils.hasLength(form.getName())) {
				errors.rejectValue("name", "course.name.required", "Please enter course name.");
			}
			
			if(form.getHours() <= 0) {
				errors.rejectValue("hours", "course.hours.required", "Please enter duration hours.");
			}
			
			if(form.getFees() < 0) {
				errors.rejectValue("fees", "course.fees.required", "Please enter course fees.");
			}
		}
	}

}
