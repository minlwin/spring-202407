package com.jdc.transaction.repo;

import java.util.Optional;

import com.jdc.transaction.service.model.ProductInfo;

public interface ProductRepo {

	Optional<ProductInfo> findById(int productId);

}
