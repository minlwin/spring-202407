package com.jdc.shop.model.master.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.repo.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepo repo;
	
	@Transactional
	public Category getOrCreate(String category) {
		return repo.findOneByName(category)
			.orElseGet(() -> repo.save(new Category(category)));
	}

}
