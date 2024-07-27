package com.jdc.spring.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.spring.BeanConfiguration;
import com.jdc.spring.jdbc.impl.DivisionDaoTemplate;

@SpringJUnitConfig(classes = BeanConfiguration.class)
public class DivisionDaoTemplateTest {

	@Autowired
	private DivisionDaoTemplate dao;
	
	@Test
	void test_find_all() {
		
		var result = dao.findAll();
		assertNotNull(result);
		assertEquals(15, result.size());
	}	
}
