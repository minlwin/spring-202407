package com.jdc.web;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int count;
	
	public int getCount() {
		return count;
	}
	
	public void countUp() {
		count ++;
	}
}
