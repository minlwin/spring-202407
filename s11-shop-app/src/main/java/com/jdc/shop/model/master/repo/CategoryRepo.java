package com.jdc.shop.model.master.repo;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.master.entity.Category;

public interface CategoryRepo extends BaseRepository<Category, Integer>{

	long countByNameIgnoreCase(String name);

}
