package com.jdc.spring.pool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.spring.pool.dto.CustomerForm;
import com.jdc.spring.pool.repo.CustomerRepo;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
@Sql(scripts = "/schema.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class CustomerRepoTest {
	
	@Autowired
	private CustomerRepo repo;
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(delimiterString = "\t", resources = "/customer.txt")
	void test_create(String name, String phone, String email, int expectedId) {
		
		var id = repo.create(new CustomerForm(name, phone, email));
		assertEquals(expectedId, id);
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		",0911112222,email@gmail.com,Please enter customer name.",
		"Thidar,,email@gmail.com,Please enter phone number.",
		"Thidar,0911112222,,Please enter email address.",
	})
	void test_create_error(String name, String phone, String email, String message) {
		
		var exception = assertThrows(IllegalArgumentException.class, 
				() -> repo.create(new CustomerForm(name, phone, email)));
		
		assertEquals(message, exception.getMessage());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvFileSource(delimiterString = "\t", resources = "/customer.txt")
	void test_find_by_id(String name, String phone, String email, int id) {
		var result = repo.findById(id);
		
		assertNotNull(result);
		assertEquals(name, result.name());
		assertEquals(phone, result.phone());
		assertEquals(email, result.email());
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource({
		",,,4",
		"aun,,,1",
		",091,,2",
		",,aung@gmail.com,1",
		"Aung Aung,091,aung,1",
		"Aung Aung,092,aung,0",
	})
	void test_search(String name, String phone, String email, int size) {
		var result = repo.search(name, phone, email);
		assertEquals(size, result.size());
	}
	
	@Order(5)
	@ParameterizedTest
	@CsvFileSource(delimiterString = "\t", resources = "/customer.txt")
	void test_update(String name, String phone, String email, int id) {
		var result = repo.update(id, new CustomerForm(name.toUpperCase(), phone, email));
		assertTrue(result);
	}
	
	@Order(6)
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	void test_delete(int id) {
		var result = repo.delete(id);
		assertTrue(result);
		
		assertNull(repo.findById(id));
	}
}
