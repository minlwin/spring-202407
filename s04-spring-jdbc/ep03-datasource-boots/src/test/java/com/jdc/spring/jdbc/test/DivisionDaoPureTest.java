package com.jdc.spring.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.jdbc.impl.DivisionDaoPure;

@SpringBootTest
public class DivisionDaoPureTest {

	@Autowired
	private DivisionDaoPure dao;
	
	@Test
	void test_find_all() {
		
		var result = dao.findAll();
		assertNotNull(result);
		assertEquals(15, result.size());
	}
}
