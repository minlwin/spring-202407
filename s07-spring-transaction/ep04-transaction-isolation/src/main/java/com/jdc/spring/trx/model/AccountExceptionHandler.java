package com.jdc.spring.trx.model;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.jdc.spring.trx.model.service.TrxHistoryService;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Configuration
public class AccountExceptionHandler {
	
	@Autowired
	private TrxHistoryService historyService;

	@Pointcut("@annotation(com.jdc.spring.trx.model.HandleAccountException)")
	public void methodToHandle() {}
	
	@AfterThrowing(throwing = "ex", value = "methodToHandle()")
	public void handle(AccountException ex) {
		log.debug("Call exception handler");
		historyService.updateStatus(ex.getHistoryId(), "Error", ex.getMessage());
	}
}
