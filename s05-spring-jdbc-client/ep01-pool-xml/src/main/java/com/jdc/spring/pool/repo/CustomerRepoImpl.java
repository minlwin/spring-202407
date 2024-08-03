package com.jdc.spring.pool.repo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.spring.pool.dto.CustomerDto;
import com.jdc.spring.pool.dto.CustomerForm;

@Repository
public class CustomerRepoImpl implements CustomerRepo {
	
	private final JdbcClient client;
	private final RowMapper<CustomerDto> rowMapper;
	
	public CustomerRepoImpl(DataSource dataSource) {
		client = JdbcClient.create(dataSource);
		rowMapper = new DataClassRowMapper<CustomerDto>(CustomerDto.class);
	}

	@Override
	public int create(CustomerForm form) {
		
		validate(form);
		
		var keyHolder = new GeneratedKeyHolder();
		
		client.sql("insert into customer(name, phone, email) values (:name, :phone, :email)")
				.paramSource(new SimplePropertySqlParameterSource(form))
				.update(keyHolder, "id");
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public CustomerDto findById(int id) {
		return client.sql("select * from customer where id = :id")
				.param("id", id)
				.query(rowMapper)
				.optional().orElse(null);
	}

	@Override
	public List<CustomerDto> search(String name, String phone, String email) {
		
		var sql = new StringBuffer("select * from customer where 1 = 1");
		var params = new MapSqlParameterSource();
		
		if(StringUtils.hasLength(name)) {
			sql.append(" and lower(name) like :name");
			params.addValue("name", name.toLowerCase().concat("%"));
		}
		
		if(StringUtils.hasLength(phone)) {
			sql.append(" and lower(phone) like :phone");
			params.addValue("phone", phone.toLowerCase().concat("%"));
		}

		if(StringUtils.hasLength(email)) {
			sql.append(" and lower(email) like :email");
			params.addValue("email", email.toLowerCase().concat("%"));
		}

		return client.sql(sql.toString())
				.paramSource(params)
				.query(rowMapper)
				.list();
	}

	@Override
	public boolean update(int id, CustomerForm form) {
		
		validate(form);
		
		return client.sql("update customer set name = :name, phone = :phone, email = :email where id = :id")
				.param("id", id)
				.param("name", form.name())
				.param("phone", form.phone())
				.param("email", form.email())
				.update() == 1;
	}

	@Override
	public boolean delete(int id) {
		return client.sql("delete from customer where id = :id")
				.param("id", id)
				.update() == 1;
	}
	
	private void validate(CustomerForm form) {
		
		if(!StringUtils.hasLength(form.name())) {
			throw new IllegalArgumentException("Please enter customer name.");
		}
		
		if(!StringUtils.hasLength(form.phone())) {
			throw new IllegalArgumentException("Please enter phone number.");
		}
		
		if(!StringUtils.hasLength(form.email())) {
			throw new IllegalArgumentException("Please enter email address.");
		}
	}
}
