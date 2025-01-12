package com.jdc.shop.controller.input;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierCodeForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Please enter supplier code.")
	private String code;
}
