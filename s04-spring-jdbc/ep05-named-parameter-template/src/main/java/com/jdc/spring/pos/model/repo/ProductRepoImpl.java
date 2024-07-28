package com.jdc.spring.pos.model.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdc.spring.pos.model.input.ProductForm;
import com.jdc.spring.pos.model.output.ProductDto;

@Repository
public class ProductRepoImpl implements ProductRepo{

	@Override
	public int create(ProductForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int id, ProductForm form) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductDto> search(Integer categoryId, String name, Integer priceFrom, Integer priceTo) {
		// TODO Auto-generated method stub
		return null;
	}

}
