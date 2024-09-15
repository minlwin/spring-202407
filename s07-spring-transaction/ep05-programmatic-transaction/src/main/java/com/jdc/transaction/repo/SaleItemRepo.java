package com.jdc.transaction.repo;

import java.util.LinkedHashMap;

import com.jdc.transaction.service.model.ProductInfo;

public interface SaleItemRepo {

	void save(int saleId, LinkedHashMap<ProductInfo, Integer> items);

}
