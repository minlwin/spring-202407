package com.jdc.spring.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC {

	private BeanA beanA;

	public BeanC(BeanA beanA) {
		super();
		this.beanA = beanA;
	}
	
	public BeanA getBeanA() {
		return beanA;
	}
}
