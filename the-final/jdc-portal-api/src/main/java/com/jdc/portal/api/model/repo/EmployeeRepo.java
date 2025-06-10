package com.jdc.portal.api.model.repo;

import java.time.LocalDate;
import java.util.List;

import com.jdc.portal.api.model.BaseRepository;
import com.jdc.portal.api.model.entity.Employee;

public interface EmployeeRepo extends BaseRepository<Employee, Long>{

	List<Employee> findByAssignAt(LocalDate now);

}
