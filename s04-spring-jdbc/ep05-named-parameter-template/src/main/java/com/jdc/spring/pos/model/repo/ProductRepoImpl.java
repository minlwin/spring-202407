package com.jdc.spring.pos.model.repo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.spring.pos.model.input.ProductForm;
import com.jdc.spring.pos.model.output.ProductDto;

@Repository
public class ProductRepoImpl implements ProductRepo{
	
	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate template;
	private RowMapper<ProductDto> rowMapper;
	
	public ProductRepoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource)
				.withTableName("product")
				.usingGeneratedKeyColumns("id");
		rowMapper = new DataClassRowMapper<ProductDto>(ProductDto.class);
	}

	@Override
	public int create(ProductForm form) {
		return insert.executeAndReturnKey(new SimplePropertySqlParameterSource(form))
				.intValue();
	}

	@Override
	public ProductDto findById(int id) {
		
		try(var stream = template.queryForStream(
				"""
				select p.id, p.name, c.id categoryId, c.name categoryName,
				p.amount, p.unit, p.price 
				from product p join category c on p.category_id = c.id 
				where p.id = :id""", 
				new MapSqlParameterSource().addValue("id", id), rowMapper)) {
			
			return stream.findAny().orElse(null);
		}
	}

	@Override
	public boolean update(int id, ProductForm form) {
		return template.update("""
				update product set name = :name, category_id = :category, amount = :amount,
				unit = :unit, price = :price where id = :id
				""", 
				new MapSqlParameterSource()
					.addValue("name", form.name())
					.addValue("category", form.categoryId())
					.addValue("amount", form.amount())
					.addValue("unit", form.unit())
					.addValue("price", form.price())
					.addValue("id", id)					
				) == 1;
	}

	@Override
	public List<ProductDto> search(
			Integer categoryId, String name, Integer priceFrom, Integer priceTo) {
		
		var sql = new StringBuffer("""
				select p.id, p.name, c.id categoryId, c.name categoryName,
				p.amount, p.unit, p.price 
				from product p join category c on p.category_id = c.id 
				where 1 = 1"""); 
		
		var params = new MapSqlParameterSource();
		
		if(null != categoryId) {
			sql.append(" and c.id = :category");
			params.addValue("category", categoryId);
		}
		
		if(StringUtils.hasLength(name)) {
			sql.append(" and lower(p.name) like :name");
			params.addValue("name", name.toLowerCase().concat("%"));
		}
		
		if(null != priceFrom) {
			sql.append(" and p.price >= :from");
			params.addValue("from", priceFrom);
		}
		
		if(null != priceTo) {
			sql.append(" and p.price <= :to");
			params.addValue("to", priceTo);
		}
		
		return template.query(sql.toString(), params, rowMapper);
	}

}
