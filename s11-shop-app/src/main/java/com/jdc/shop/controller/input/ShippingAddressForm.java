package com.jdc.shop.controller.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShippingAddressForm {

	private String id;
	
	@NotBlank(message = "Please enter name.")
	private String name;
	@NotBlank(message = "Please enter building.")
	private String building;
	@NotBlank(message = "Please enter street.")
	private String street;
	@NotBlank(message = "Please enter quarter.")
	private String quarter;
	@NotBlank(message = "Please enter township.")
	private String township;
	@NotBlank(message = "Please enter region.")
	private String region;	

}
