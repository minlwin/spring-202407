package com.jdc.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.sping.Application;
import com.jdc.sping.model.DivisionDao;
import com.jdc.sping.model.dto.DivisionForm;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(value = OrderAnnotation.class)
public class DivisionDaoTest {

	@Autowired
	private DivisionDao dao;	

	@Order(1)
	@ParameterizedTest
	@CsvFileSource(
			delimiterString = "\t",
			resources = "/test_create.txt")
	void test_create(String name, String capital, String region, int expectedId) {
		
		// Prepare Input
		var form = new DivisionForm(name, capital, region);
		
		// Execute method
		var result = dao.create(form);
		
		// Check Result
		assertEquals(expectedId, result);
	}
	
	@Order(2)
	@Test
	void test_count_all() {
		assertEquals(15L, dao.countAll());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvFileSource(
			delimiterString = "\t",
			resources = "/test_create.txt")
	void test_find_by_id(String name, String capital, String region, int id) {
		
		var result = dao.findById(id);
		assertNotNull(result);
		assertEquals(name, result.name());
		assertEquals(capital, result.capital());
		assertEquals(region, result.region());
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource(value = {
			"Ayeyarwady Region,1",
			"Bago Region,1",
			"Chin State,1",
			"Kachin State,1",
			"Kayah State,1",
			"Kayin State,1",
			"Magway Region,1",
			"Mandalay Region,1",
			"Mon State,1",
			"Naypyidaw Union Territory,1",
			"Rakhine State,1",
			"Sagaing Region,1",
			"Shan State,1",
			"Tanintharyi Region,1",
			"Yangon Region,1"			
	})
	void test_find_by_name(String name, int expectedSize) {
		var list = dao.findByName(name);
		assertEquals(expectedSize, list.size());
	}
	
	@Order(5)
	@ParameterizedTest
	@CsvSource(value = {
		"Pathein,1",
		"Bago,1",
		"Hakha,1",
		"Myitkyina,1",
		"Loikaw,1",
		"Hpa-an,1",
		"Magwe,1",
		"Mandalay,1",
		"Mawlamyine,1",
		"Naypyidaw,1",
		"Sittwe,1",
		"Monywa,1",
		"Taunggyi,1",
		"Dawei,1",
		"Yangon,1"})
	void test_find_by_capital(String capital, int expectedSize) {
		var list = dao.findByCapital(capital);
		assertEquals(expectedSize, list.size());
	}
	
	
	@Order(6)
	@ParameterizedTest
	@CsvSource({
		"North,4",
		"East,3",
		"Central,5",
		"South,5",
		"West,3"
	})
	void test_find_by_region(String region, int expectedSize) {
		var list = dao.findByRegion(region);
		assertEquals(expectedSize, list.size());
	}
}
