package com.jdc.shop.controller.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter customer name.")
	private String name;
	@NotBlank(message = "Please enter email address.")
	private String email;
	@NotBlank(message = "Please enter password.")
	private String password;
	@NotBlank(message = "Please phone number.")
	private String phone;
}
