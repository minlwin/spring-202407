package com.jdc.spring.pos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.spring.pos.model.input.ProductForm;
import com.jdc.spring.pos.model.repo.ProductRepo;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(value = OrderAnnotation.class)
@Sql(scripts = "classpath:/sql/category.sql", 
	executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class ProductRepoTest {
	
	@Autowired
	private ProductRepo repo;
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(delimiter = '\t', resources = "/data/product/test_create.txt")
	void test_create(int categoryId, String name, int amount, String unit, int price, int expectedId) {
		var input = new ProductForm(name, categoryId, amount, unit, price);
		var id = repo.create(input);
		assertEquals(expectedId, id);
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(delimiter = '\t', resources = "/data/product/test_find_by_id.txt")
	void test_find_by_id(String categoryName, int categoryId, String name, int amount, String unit, int price, int id) {
		
		var result = repo.findById(id);
		assertNotNull(result);
		
		assertEquals(categoryName, result.categoryName());
		assertEquals(categoryId, result.categoryId());
		assertEquals(name, result.name());
		assertEquals(amount, result.amount());
		assertEquals(unit, result.unit());
		assertEquals(price, result.price());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		",,,,14",
		"2,,,,3",
		",T,,,4",
		"2,T,,,1",
		",,15000,,6",
		",,,15000,9",
		",,12000,15000,2",
		",Bief Tong,12000,15000,1",
		"4,,12000,15000,2",
		",Tiger Shrimp,20000,39000,0",
	})
	void test_search(Integer category, String name, Integer from, Integer to, int size) {
		var list = repo.search(category, name, from, to);
		assertEquals(size, list.size());
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvFileSource(delimiter = '\t', resources = "/data/product/test_find_by_id.txt")
	void test_update(String categoryName, int categoryId, String name, int amount, String unit, int price, int id) {

		var input = new ProductForm(name.toUpperCase(), categoryId, amount, unit, price + 500);
		
		assertTrue(repo.update(id, input));
		
		var result = repo.findById(id);
		assertEquals(categoryName, result.categoryName());
		assertEquals(input.categoryId(), result.categoryId());
		assertEquals(input.name(), result.name());
		assertEquals(input.amount(), result.amount());
		assertEquals(input.unit(), result.unit());
		assertEquals(input.price(), result.price());
	}
}
