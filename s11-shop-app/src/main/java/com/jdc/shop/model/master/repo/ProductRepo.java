package com.jdc.shop.model.master.repo;

import java.util.Optional;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.master.entity.Product;

public interface ProductRepo extends BaseRepository<Product, Integer>{

	Optional<Product> findOneByCategoryIdAndName(int categoryId, String name);
}
