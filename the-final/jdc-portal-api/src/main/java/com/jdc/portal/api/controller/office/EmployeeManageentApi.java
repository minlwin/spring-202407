package com.jdc.portal.api.controller.office;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.portal.api.controller.office.input.EmployeeForm;
import com.jdc.portal.api.controller.office.input.EmployeeSearch;
import com.jdc.portal.api.controller.office.output.EmployeeDetails;
import com.jdc.portal.api.controller.office.output.EmployeeDto;
import com.jdc.portal.api.service.EmployeeService;
import com.jdc.portal.api.utils.exceptions.EmployeeInitializationRequired;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("office/employees")
public class EmployeeManageentApi {

	private final EmployeeService service;
	
	@GetMapping
	@EmployeeInitializationRequired(false)
	List<EmployeeDto> search(EmployeeSearch search) {
		return service.search(search);
	}
	
	@PostMapping
	EmployeeDetails create(@Validated @RequestBody EmployeeForm form, BindingResult bindingResult) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	EmployeeDetails update(
			@PathVariable long id,
			@Validated @RequestBody EmployeeForm form, BindingResult bindingResult) {
		return service.update(id, form);
	}
	
	@GetMapping("{id}")
	EmployeeDetails findById(@PathVariable long id) {
		return service.findById(id);
	}
	
}
