package com.jdc.spring.bean;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.spring.BeanConfiguration;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitConfig(value = BeanConfiguration.class)
public class JavaBaseScopeTest {

	@Autowired
	Container prototypeBean;
	
	@Autowired
	Container singletonBean;
	
	@Order(1)
	@ParameterizedTest
	@ValueSource(strings = {
		"Hello Spring",
		"Hello IocContainer",
		"Hello Spring Boots"
	})
	void test_add(String message) {
		
		System.out.println(singletonBean);
		System.out.println(prototypeBean);
		
		prototypeBean.add(message);
		singletonBean.add(message);
	}
	
	@Order(2)
	@Test
	void test_get_all() {
		System.out.println("Prototype");
		System.out.println(prototypeBean.getAll());
		
		System.out.println();
		System.out.println("Singlaton");
		System.out.println(singletonBean.getAll());
	}
}
