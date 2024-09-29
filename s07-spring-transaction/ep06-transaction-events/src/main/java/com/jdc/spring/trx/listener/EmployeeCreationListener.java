package com.jdc.spring.trx.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jdc.spring.trx.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeCreationListener {

	@TransactionalEventListener
	public void handleAfterCommit(Employee employee) {
		log.debug("Before Commit : {}", employee);
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
	public void handleAfterRollback(Employee employee) {
		log.error("Rolling back : {}", employee);
	}
}
