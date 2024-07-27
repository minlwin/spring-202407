package com.jdc.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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
}
