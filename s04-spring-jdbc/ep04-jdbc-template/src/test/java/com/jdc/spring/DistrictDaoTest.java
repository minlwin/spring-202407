package com.jdc.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.sping.Application;
import com.jdc.sping.model.DistrictDao;
import com.jdc.sping.model.dto.DistrictForm;

@Sql(
	scripts = "classpath:/sql/division.sql", 
	executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
@TestMethodOrder(value = OrderAnnotation.class)
@SpringBootTest(classes = Application.class)
public class DistrictDaoTest {
	
	@Autowired
	private DistrictDao dao;

	@Order(1)
	@ParameterizedTest
	@CsvFileSource(resources = "/data/district/test_create.txt", delimiterString = "\t")
	void test_create(int divisionId, String name, int expectedId) {
		var input = new DistrictForm(divisionId, name);
		var id = dao.create(input);
		
		assertEquals(expectedId, id);
	}
	
	@Test
	@Order(2)
	void test_count_all() {
		var count = dao.countAll();
		assertEquals(14, count);
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvFileSource(resources = "/data/district/test_find_by_id.txt", delimiterString = "\t")
	void test_find_by_id(String divisionName, int divisionId, String name, int id, String region) {
		
		var result = dao.findById(id);
		assertNotNull(result);
		
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(divisionId, result.divisionId());
		assertEquals(divisionName, result.divisionName());
		assertEquals(region, result.region());
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource({
		",,,14",
		"1,,,8",
		",South,,14",
		",Central,,6",
		"1,South,,8",
		"1,South,m,3",
	})
	void test_search(Integer divisionId, String region, String name, int expectedCount) {
		var result = dao.search(divisionId, region, name);
		assertEquals(expectedCount, result.size());
	}
	
	@Order(5)
	@ParameterizedTest
	@CsvFileSource(resources = "/data/district/test_create.txt", delimiterString = "\t")
	void test_update(int divisionId, String name, int id) {
		var form = new DistrictForm(divisionId, name.toUpperCase());
		var result = dao.update(id, form);
		assertTrue(result);
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void test_delete(int id) {
		assertTrue(dao.delete(id));
		assertNull(dao.findById(id));
	}
}
