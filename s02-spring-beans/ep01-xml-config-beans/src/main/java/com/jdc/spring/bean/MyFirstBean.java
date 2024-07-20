package com.jdc.spring.bean;

public class MyFirstBean {
	
	private String value;
	private String name;
	private String alias;
	
	public MyFirstBean() {
	}
	
	public MyFirstBean(String value) {
		this.value = value;
	}

	public MyFirstBean(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
