package com.jdc.shop.controller.input;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Please enter supplier name.")
	private String name;
	@NotBlank(message = "Please enter phone number.")
	private String phone;
	@NotBlank(message = "Please enter email address.")
	private String email;
	private String shopName;
	private String address;
}
