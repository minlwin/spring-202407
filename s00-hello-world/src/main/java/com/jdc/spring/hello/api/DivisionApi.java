package com.jdc.spring.hello.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.hello.model.Division;
import com.jdc.spring.hello.model.DivisionRepo;

@RestController
@RequestMapping("division")
public class DivisionApi {
	
	@Autowired
	private DivisionRepo divisionRepo;

	@GetMapping
	List<Division> findAll() {
		return divisionRepo.findAll();
	}
}
