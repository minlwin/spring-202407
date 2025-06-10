package com.jdc.portal.api.controller.office.output;

import java.time.LocalDate;

import com.jdc.portal.api.model.dto.Contact_;
import com.jdc.portal.api.model.entity.Account_;
import com.jdc.portal.api.model.entity.Employee;
import com.jdc.portal.api.model.entity.Employee.Type;
import com.jdc.portal.api.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record EmployeeDto(
		long id,
		String name,
		String email,
		String phone,
		Type type,
		LocalDate dob,
		LocalDate assignAt,
		LocalDate resignAt) {

	public static void select(CriteriaQuery<EmployeeDto> cq, Root<Employee> root) {
		cq.multiselect(
			root.get(Employee_.id),
			root.get(Employee_.name),
			root.get(Employee_.account).get(Account_.email),
			root.get(Employee_.contact).get(Contact_.phone),
			root.get(Employee_.type),
			root.get(Employee_.dob),
			root.get(Employee_.assignAt),
			root.get(Employee_.resignAt)
		);
	}

}
