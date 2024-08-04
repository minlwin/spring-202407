package com.jdc.spring.aop.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.spring.aop.aspects.CheckResult;
import com.jdc.spring.aop.model.RegistrationDto;
import com.jdc.spring.aop.model.RegistrationForm;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {
	
	private JdbcClient client;
	private RowMapper<RegistrationDto> rowMapper;
	
	public RegistrationDaoImpl(DataSource dataSource) {
		this.client = JdbcClient.create(dataSource);
		this.rowMapper = new DataClassRowMapper<>(RegistrationDto.class);
	}

	@Override
	public int create(RegistrationForm form) {
		
		var keyHolder = new GeneratedKeyHolder();
		
		client.sql("insert into registration (course, fees, student, phone, email) values (:course, :fees, :student, :phone, :email)")
			.paramSource(form)
			.update(keyHolder, "id");
		
		return keyHolder.getKey().intValue();
	}

	@Override
	@CheckResult
	public RegistrationDto findById(int id) {
		return client.sql("select * from registration where id = :id")
				.param("id", id)
				.query(rowMapper)
				.optional().orElse(null);
	}

	@Override
	public List<RegistrationDto> search(String keyword, LocalDate from, LocalDate to) {
		
		var sql = new StringBuffer("select * from registration where 1 = 1");
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(keyword)) {
			sql.append("""
					 and (
					lower(course) like :keyword or 
					lower(student) like :keyword or 
					lower(phone) like :keyword or 
					lower(email) like :keyword)""");
			params.put("keyword", keyword.toLowerCase().concat("%"));
		}
		
		if(null != from) {
			sql.append(" and regist_at >= :from");
			params.put("from", from.atStartOfDay());
		}
		
		if(null != to) {
			sql.append(" and regist_at < :to");
			params.put("to", to.plusDays(1).atStartOfDay());
		}
		
		return client.sql(sql.toString())
				.params(params)
				.query(rowMapper)
				.list();
	}

}
