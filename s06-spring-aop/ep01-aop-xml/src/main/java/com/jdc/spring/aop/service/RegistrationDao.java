package com.jdc.spring.aop.service;

import java.time.LocalDate;
import java.util.List;

import com.jdc.spring.aop.model.RegistrationDto;
import com.jdc.spring.aop.model.RegistrationForm;

public interface RegistrationDao {

	int create(RegistrationForm form);
	
	RegistrationDto findById(int id);
	
	List<RegistrationDto> search(String keyword, LocalDate from, LocalDate to);
}
