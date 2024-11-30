package com.jdc.spring.controller.input;

import com.jdc.spring.model.entity.Registration;
import com.jdc.spring.model.entity.Section;

public record RegistrationForm() {

	public static RegistrationForm from(Registration entity) {
		return null;
	}

	public static RegistrationForm from(Section entity) {
		return null;
	}
}
