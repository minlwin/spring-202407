package com.jdc.spring.bean;

public class MyFirstBeanFactoryBean {

	public MyFirstBean createWithAllArgs(
			String name, 
			String alias,
			String value) {
		var bean = new MyFirstBean();
		bean.setName(name);
		bean.setAlias(alias);
		bean.setValue(value);
		return bean;
	}
}
