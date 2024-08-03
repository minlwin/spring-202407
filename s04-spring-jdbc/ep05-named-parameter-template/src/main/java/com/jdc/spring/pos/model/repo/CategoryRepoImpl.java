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

import com.jdc.spring.pos.model.input.CategoryForm;
import com.jdc.spring.pos.model.output.CategoryDto;

@Repository
public class CategoryRepoImpl implements CategoryRepo {
	
	private final SimpleJdbcInsert insert;
	private final NamedParameterJdbcTemplate template;
	private final RowMapper<CategoryDto> rowMapper;
	
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
		
		var sql = new StringBuffer("select * from category");
		var params = new MapSqlParameterSource();
		
		if(StringUtils.hasLength(keyword)) {
			sql.append(" where lower(name) like :keyword");
			params.addValue("keyword", keyword.toLowerCase().concat("%"));
		}
		
		return template.query(sql.toString(), params, rowMapper);
	}

	@Override
	public boolean update(int id, CategoryForm form) {
		return template.update("update category set name = :name where id = :id", 
				new MapSqlParameterSource()
					.addValue("id", id)
					.addValue("name", form.name())) == 1;
	}

}
