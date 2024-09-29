package com.jdc.spring.trx.repository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.domain.Employee;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final ApplicationEventPublisher publisher;
	
	@Transactional
	public int create(Employee employee) {
		publisher.publishEvent(employee);
		return jdbcTemplate.update("insert into employee values (:code, :name, :phone, :role, :entryDate)", 
				new SimplePropertySqlParameterSource(employee));
	}
}
