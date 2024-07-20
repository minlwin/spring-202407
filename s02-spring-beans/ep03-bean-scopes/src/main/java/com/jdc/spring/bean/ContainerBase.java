package com.jdc.spring.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerBase implements Container {

	private List<String> list = new ArrayList<>();
	
	@Override
	public void add(String str) {
		list.add(str);
	}
	
	@Override
	public List<String> getAll() {
		return List.copyOf(list);
	}
}
