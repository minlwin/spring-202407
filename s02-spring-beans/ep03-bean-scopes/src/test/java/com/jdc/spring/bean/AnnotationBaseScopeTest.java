package com.jdc.spring.bean;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitConfig(locations = "classpath:/application.xml")
public class AnnotationBaseScopeTest {

	@Autowired
	Container prototypeContainer;
	
	@Autowired
	Container singletonContainer;
	
	@Order(1)
	@ParameterizedTest
	@ValueSource(strings = {
		"Hello Spring",
		"Hello IocContainer",
		"Hello Spring Boots"
	})
	void test_add(String message) {
		
		System.out.println(singletonContainer);
		System.out.println(prototypeContainer);
		
		singletonContainer.add(message);
		prototypeContainer.add(message);
	}
	
	@Order(2)
	@Test
	void test_get_all() {
		System.out.println("Prototype");
		System.out.println(prototypeContainer.getAll());
		
		System.out.println();
		System.out.println("Singlaton");
		System.out.println(singletonContainer.getAll());
	}
}
