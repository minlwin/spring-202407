package com.jdc.transaction.repo;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdc.transaction.service.model.ProductInfo;

@Repository
public class ProductRepoImpl implements ProductRepo {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<ProductInfo> rowMapper = new DataClassRowMapper<>(ProductInfo.class);

	@Override
	public Optional<ProductInfo> findById(int productId) {
		return template.query("""
				select p.id, p.name, p.unit_price, p.category_id, c.name category from PRODUCT p 
				join CATEGORY c on c.id = p.category_id where p.id = :id
				""", 
				Map.of("id", productId), rowMapper)
				.stream().findAny();
	}

}
