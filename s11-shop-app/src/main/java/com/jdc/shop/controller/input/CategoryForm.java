package com.jdc.shop.controller.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryForm {

	private Integer id;
	
	@NotBlank(message = "Please enter category name.")
	private String name;
}
