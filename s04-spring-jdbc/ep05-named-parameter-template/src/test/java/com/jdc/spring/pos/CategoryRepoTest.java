package com.jdc.spring.pos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.pos.model.input.CategoryForm;
import com.jdc.spring.pos.model.repo.CategoryRepo;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringBootTest(classes = Application.class)
public class CategoryRepoTest {
	
	@Autowired
	private CategoryRepo repo;

	@Order(1)
	@ParameterizedTest
	@CsvSource({
		"1,Fruits",
		"2,Vegitable",
		"3,Fish And Seafood",
		"4,Butchery"
	})
	void test_create(int expectedId, String name) {
		var id = repo.create(new CategoryForm(name));
		assertEquals(expectedId, id);
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"1,Fruits",
		"2,Vegitable",
		"3,Fish And Seafood",
		"4,Butchery"
	})
	void test_find_by_id(int id, String name) {
		var result = repo.findById(id);
	
		assertNotNull(result);
		assertEquals(name, result.name());
	}

	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = {5, 6, 6})
	void test_find_by_id_not_found(int id) {
		var result = repo.findById(id);
		assertNull(result);
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource({
		"F,2",
		"Fruits,1",
		",4"
	})
	void test_search(String keyword, int size) {
		var list = repo.search(keyword);
		assertEquals(size, list.size());
	}
	
	@Order(5)
	@ParameterizedTest
	@CsvSource({
		"1,Fruits",
		"2,Vegitable",
		"3,Fish And Seafood",
		"4,Butchery"
	})
	void update(int id, String name) {
		var form = new CategoryForm(name.toUpperCase());
		assertTrue(repo.update(id, form));
		
		var result = repo.findById(id);
		assertEquals(form.name(), result.name());
	}
	
	@Order(6)
	@ParameterizedTest
	@CsvSource({
		"5,Fruits",
		"6,Vegitable",
		"7,Fish And Seafood",
		"8,Butchery"
	})
	void update_not_found(int id, String name) {
		var form = new CategoryForm(name.toUpperCase());
		assertFalse(repo.update(id, form));
	}
}
