package com.jdc.portal.api.service;

import static com.jdc.portal.api.utils.EntityOperationUtils.safeCall;

import java.util.List;
import java.util.function.Function;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.portal.api.controller.office.input.EmployeeForm;
import com.jdc.portal.api.controller.office.input.EmployeeSearch;
import com.jdc.portal.api.controller.office.output.EmployeeDetails;
import com.jdc.portal.api.controller.office.output.EmployeeDto;
import com.jdc.portal.api.model.entity.Employee;
import com.jdc.portal.api.model.repo.EmployeeRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepo employeeRepo;
	private final PasswordEncoder passwordEncoder;
	
	@PreAuthorize("hasRole('OFFICE')")
	public List<EmployeeDto> search(EmployeeSearch search) {
		return employeeRepo.search(queryFun(search));
	}

	@PreAuthorize("hasRole('OFFICE')")
	public EmployeeDetails create(EmployeeForm form) {
		var entity = employeeRepo.persist(form.getEntity(passwordEncoder));
		return EmployeeDetails.from(entity);
	}

	@PreAuthorize("hasRole('OFFICE') || authentication.name eq #form.username")
	public EmployeeDetails update(long id, EmployeeForm form) {
		var entity = safeCall(employeeRepo.findById(id), "Employee", id);
		form.update(entity);
		return EmployeeDetails.from(entity);
	}

	@PreAuthorize("hasRole('OFFICE') || authentication.name eq #form.username")
	public EmployeeDetails findById(long id) {
		return safeCall(employeeRepo.findById(id).map(EmployeeDetails::from), "Employee", id);
	}

	private Function<CriteriaBuilder, CriteriaQuery<EmployeeDto>> queryFun(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(EmployeeDto.class);
			var root = cq.from(Employee.class);
			EmployeeDto.select(cq, root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}

}
