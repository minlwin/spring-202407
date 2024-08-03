package com.jdc.spring.pool.repo;

import java.util.List;

import com.jdc.spring.pool.dto.CustomerDto;
import com.jdc.spring.pool.dto.CustomerForm;

public interface CustomerRepo {

	int create(CustomerForm form);
	
	CustomerDto findById(int id);
	
	List<CustomerDto> search(String name, String phone, String email);
	
	boolean update(int id, CustomerForm form);
	
	boolean delete(int id);
}
