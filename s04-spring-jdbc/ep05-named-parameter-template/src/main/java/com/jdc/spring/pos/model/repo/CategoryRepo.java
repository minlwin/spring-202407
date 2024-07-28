package com.jdc.spring.pos.model.repo;

import java.util.List;

import com.jdc.spring.pos.model.input.CategoryForm;
import com.jdc.spring.pos.model.output.CategoryDto;

public interface CategoryRepo {

	int create(CategoryForm form);
	
	CategoryDto findById(int id);
	
	List<CategoryDto> search(String keyword);
	
	boolean update(int id, CategoryForm form);
}
