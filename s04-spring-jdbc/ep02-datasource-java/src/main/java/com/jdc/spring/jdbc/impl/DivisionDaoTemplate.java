package com.jdc.spring.jdbc.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdc.spring.jdbc.DivisionDao;
import com.jdc.spring.jdbc.model.Division;

@Repository
public class DivisionDaoTemplate implements DivisionDao {
	
	private static final String SQL = "select * from division";
	
	private JdbcTemplate template;
	
	public DivisionDaoTemplate(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Division> findAll() {
		return template.query(SQL, new BeanPropertyRowMapper<>(Division.class));
	}

}
