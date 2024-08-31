package com.jdc.spring.trx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.service.CashInService;
import com.jdc.spring.trx.utils.BusinessException;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitConfig(classes = DatabaseConfig.class)
public class CashInServiceTest {
	
	@Autowired
	private CashInService service;

	@Order(1)
	@ParameterizedTest
	@CsvSource(value = {
		"aung@gmail.com,MPU,999,Test Cash In,Transaction is under minimum limit 1000.",	
		"thidar@gmail.com,MPU,999,Test Cash In,Transaction is under minimum limit 1000.",	
		"nilar@gmail.com,MPU,2999,Test Cash In,Transaction is under minimum limit 3000.",	
		"kyaw@gmail.com,MPU,2999,Test Cash In,Transaction is under minimum limit 3000."
	})
	void test_create_lower_limit_error(
			String account,
			String cashInFrom,
			int amount,
			String particular, 
			String message) {
		
		var form = new CashInForm(account, cashInFrom, amount, particular);
		
		var exception = assertThrows(BusinessException.class, () -> service.cashIn(form));
		
		assertEquals(message, exception.getMessage());
	}

	@Order(2)
	void test_create_upper_limit_error() {
		
	}
	
	@Order(3)
	void test_create_daily_limit_error() {
		
	}
	
	@Order(4)
	void test_create_constraint_error() {
		
	}
	
	
}
