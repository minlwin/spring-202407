package com.jdc.spring.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB {

	private BeanC beanC;

	public BeanB(BeanC beanC) {
		super();
		this.beanC = beanC;
	}
	
	public BeanC getBeanC() {
		return beanC;
	}
}
