package com.jdc.transaction.repo;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.jdc.transaction.service.model.ProductInfo;

@Repository
public class SaleItemRepoImpl implements SaleItemRepo {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public void save(int saleId, LinkedHashMap<ProductInfo, Integer> items) {
		
		template.batchUpdate("""
				insert into SALE_ITEM (sale_id, product_id, unit_price, quantity) 
				values (:sale, :product, :price, :quantity)
				""", 
				items.entrySet().stream()
					.map(entry -> new MapSqlParameterSource()
							.addValue("sale", saleId)
							.addValue("price", entry.getKey().unitPrice())
							.addValue("quantity", entry.getValue())
							.addValue("product", entry.getKey().id()))
					.toArray(size -> new SqlParameterSource[size]));
	}

}
