package com.jdc.spring.trx;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.spring.trx.model.AccountException;
import com.jdc.spring.trx.model.service.AccountService;

@SpringBootTest
public class AccountServiceTest {
	
	@Autowired
	private AccountService service;

	@Test
	void test_transfer() {
		
		assertThrows(AccountException.class, () -> service.transfer(1, 3, 500001));
		
		var accounts = service.findAll();
		
		for(var account : accounts) {
			System.out.println(account);
		}
	}
	
	@Test
	@Disabled
	void test_add() {
		var accounts = service.findAll();
		
		for(var account : accounts) {
			System.out.println(account);
		}
		
		service.add(50000);
		
		accounts = service.findAll();
		
		for(var account : accounts) {
			System.out.println(account);
		}
	}
}
