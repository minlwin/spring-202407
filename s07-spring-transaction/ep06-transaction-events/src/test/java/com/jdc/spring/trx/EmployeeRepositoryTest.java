package com.jdc.spring.trx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.trx.args.EmployeeAgregator;
import com.jdc.spring.trx.domain.Employee;
import com.jdc.spring.trx.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository repository;

	@ParameterizedTest
	@CsvSource({
		"EMP001,Aung Aung,091817171,Employee",
		"EMP002,Thidar,091817172,Manager",
	})
	void test_success(@AggregateWith(value = EmployeeAgregator.class) Employee employee) {
		assertEquals(1, repository.create(employee));
	}
	
	@ParameterizedTest
	@CsvSource({
		"EMP001,Aung Aung,091817171,Employee",
		"EMP002,Thidar,091817172,Manager",
	})
	void test_error(@AggregateWith(value = EmployeeAgregator.class) Employee employee) {
		assertThrows(RuntimeException.class, () -> repository.create(employee));
	}
}
