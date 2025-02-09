package com.jdc.shop.controller.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFeature {

	@NotBlank(message = "Please enter feature name.")
	private String name;
	@NotBlank(message = "Please enter feature value.")
	private String feature;
	
	private boolean deleted;
}
