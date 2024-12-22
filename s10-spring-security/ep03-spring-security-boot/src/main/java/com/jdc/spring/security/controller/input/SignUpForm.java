package com.jdc.spring.security.controller.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter member name.")
	private String name;

	@NotBlank(message = "Please enter email address.")
	private String email;
	
	@NotBlank(message = "Please enter password.")
	private String password;
}
