package com.jdc.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {
	
	@Autowired
	private BeanB beanB;

	public BeanB getBeanB() {
		return beanB;
	}
}
