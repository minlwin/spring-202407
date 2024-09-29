package com.jdc.spring.trx.domain;

import java.time.LocalDate;

public record Employee(
		String code,
		String name,
		String phone,
		String role,
		LocalDate entryDate
		){

}
