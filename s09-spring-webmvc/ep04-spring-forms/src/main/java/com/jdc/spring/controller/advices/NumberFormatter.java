package com.jdc.spring.controller.advices;

import java.text.DecimalFormat;

public class NumberFormatter {

	private static final DecimalFormat fmt = new DecimalFormat("#,##0");
	
	public String format(Number number) {
		return fmt.format(number);
	}
}
