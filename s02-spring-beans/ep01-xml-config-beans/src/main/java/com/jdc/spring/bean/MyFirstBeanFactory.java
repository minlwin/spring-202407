package com.jdc.spring.bean;

public class MyFirstBeanFactory {

	public static MyFirstBean createWithDefault() {
		return new MyFirstBean("Hello static factory");
	}

	public static MyFirstBean createWithName(String name) {
		var bean = new MyFirstBean();
		bean.setName(name);
		return bean;
	}
	
	public static MyFirstBean createWithValue(String value) {
		var bean = new MyFirstBean();
		bean.setValue(value);
		return bean;
	}
	
	public static MyFirstBean createWithNameAndValue(String name, String value) {
		var bean = new MyFirstBean();
		bean.setName(name);
		bean.setValue(value);
		return bean;
	}
	
}
