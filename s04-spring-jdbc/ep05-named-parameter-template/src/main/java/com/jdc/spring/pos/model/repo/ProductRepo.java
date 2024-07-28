package com.jdc.spring.pos.model.repo;

import java.util.List;

import com.jdc.spring.pos.model.input.ProductForm;
import com.jdc.spring.pos.model.output.ProductDto;

public interface ProductRepo {

	int create(ProductForm form);
	
	ProductDto findById(int id);
	
	boolean update(int id, ProductForm form);
	
	List<ProductDto> search(
			Integer categoryId, 
			String name, 
			Integer priceFrom, 
			Integer priceTo);
}
