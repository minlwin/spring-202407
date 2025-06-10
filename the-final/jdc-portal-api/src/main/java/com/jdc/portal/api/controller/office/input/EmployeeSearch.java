package com.jdc.portal.api.controller.office.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.portal.api.model.dto.Contact_;
import com.jdc.portal.api.model.entity.Account_;
import com.jdc.portal.api.model.entity.Employee;
import com.jdc.portal.api.model.entity.Employee_;
import com.jdc.portal.api.model.entity.Employee.Type;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record EmployeeSearch(
		Type type,
		LocalDate assignFrom,
		LocalDate assignTo,
		LocalDate resignFrom,
		LocalDate resignTo,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Employee> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(null != type) {
			predicates.add(cb.equal(root.get(Employee_.type), type));
		}
		
		if(null != assignFrom) {
			predicates.add(cb.greaterThanOrEqualTo(root.get(Employee_.assignAt), assignFrom));
		}
		
		if(null != assignTo) {
			predicates.add(cb.lessThanOrEqualTo(root.get(Employee_.assignAt), assignTo));
		}

		if(null != resignFrom) {
			predicates.add(cb.greaterThanOrEqualTo(root.get(Employee_.resignAt), resignFrom));
		}

		if(null != resignTo) {
			predicates.add(cb.lessThanOrEqualTo(root.get(Employee_.resignAt), resignTo));
		}

		if(StringUtils.hasLength(keyword)) {
			predicates.add(cb.or(
				cb.like(cb.lower(root.get(Employee_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Employee_.account).get(Account_.email)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Employee_.contact).get(Contact_.phone)), keyword.toLowerCase().concat("%"))
			));
		}
		
		return predicates.toArray(size -> new Predicate[size]);
	}

}
