package com.jdc.portal.api.utils.schedules;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.portal.api.model.repo.EmployeeRepo;

@Service
public class EmployeeActivationSchedularService {

	private EmployeeRepo employeeRepo;
	
	@Transactional
	@Scheduled(cron = "0 0 1 * * *")
	public void execute() {
		var employees = employeeRepo.findByAssignAt(LocalDate.now());
		for(var employee : employees) {
			employee.getAccount().setActivated(true);
			employee.getAccount().setActivatedAt(LocalDateTime.now());
		}
	}
}
