package com.jdc.shop.controller.input;

import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.master.entity.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductForm {

	private int id;
	@NotBlank(message = "Please enter product name.")
	private String name;
	@NotNull(message = "Please enter sale price.")
	private Integer salePrice;
	private String description;
	private List<@Valid ProductFeature> features = new ArrayList<>();
	
	public static ProductForm from(Product product) {
		var form =  new ProductForm();
		
		form.setId(product.getId());
		form.setName(product.getName());
		form.setSalePrice(product.getSalePrice());
		form.setDescription(product.getDescription());
		var features = product.getProperties().entrySet().stream()
				.map(item -> new ProductFeature(item.getKey(), item.getValue(), false)).toList();
		form.getFeatures().addAll(features);
		
		if(form.getFeatures().size() == 0) {
			form.getFeatures().add(new ProductFeature());
		}
		return form;
	}
}
