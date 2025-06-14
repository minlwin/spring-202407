package com.jdc.portal.api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PasswordGenerator {
	
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMdd");

	public static String generate(String name) {
		var temp = name.replaceAll(" ", "").replaceAll("a", "@").replaceAll("A", "@");
		var sb = new StringBuffer();
		sb.append(temp);
		sb.reverse();
		var now = LocalDate.now().format(DTF);
		return "%s#%s".formatted(sb.toString(), now);
	}
	
	public static void main(String[] args) {
		var password = generate("Zaw Min Lwin");
		System.out.println(password);
	}
}
