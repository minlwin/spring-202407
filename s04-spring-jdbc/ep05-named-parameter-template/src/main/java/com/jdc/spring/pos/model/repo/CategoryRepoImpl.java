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

import com.jdc.spring.pos.model.input.CategoryForm;
import com.jdc.spring.pos.model.output.CategoryDto;

@Repository
public class CategoryRepoImpl implements CategoryRepo {
	
	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate template;
	private RowMapper<CategoryDto> rowMapper;
	
	public CategoryRepoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource)
				.withTableName("category")
				.usingGeneratedKeyColumns("id");
		
		rowMapper = new DataClassRowMapper<>(CategoryDto.class);
	}

	@Override
	public int create(CategoryForm form) {
		return insert.executeAndReturnKey(new SimplePropertySqlParameterSource(form))
				.intValue();
	}

	@Override
	public CategoryDto findById(int id) {
		try(var stream = template.queryForStream("select * from category where id = :id", 
				new MapSqlParameterSource()
					.addValue("id", id) , 
				rowMapper)) {
			return stream.findAny().orElse(null);
		}
	}

	@Override
	public List<CategoryDto> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int id, CategoryForm form) {
		// TODO Auto-generated method stub
		return false;
	}

}
