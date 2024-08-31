package com.jdc.spring.trx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.service.CashInService;
import com.jdc.spring.trx.utils.BusinessException;
import com.jdc.spring.trx.utils.ValidationException;

@ActiveProfiles("testing")
@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, scripts = "classpath:/sql/test/cash_in_daily_limit.sql")
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
	@ParameterizedTest
	@CsvSource(value = {
		"aung@gmail.com,MPU,100001,Test Cash In,Transaction is exeed maximum limit 100000.",	
		"thidar@gmail.com,MPU,100001,Test Cash In,Transaction is exeed maximum limit 100000.",	
		"nilar@gmail.com,MPU,300001,Test Cash In,Transaction is exeed maximum limit 300000.",	
		"kyaw@gmail.com,MPU,300001,Test Cash In,Transaction is exeed maximum limit 300000."
	})
	void test_create_upper_limit_error(
			String account,
			String cashInFrom,
			int amount,
			String particular, 
			String message) {
		
		var form = new CashInForm(account, cashInFrom, amount, particular);
		
		var exception = assertThrows(BusinessException.class, () -> service.cashIn(form));
		
		assertEquals(message, exception.getMessage());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource(value = {
		"aung@gmail.com,MPU,100000,Test Cash In,Transaction is exeed daily maximum limit 299999.",	
		"nilar@gmail.com,MPU,100000,Test Cash In,Transaction is exeed daily maximum limit 899999.",	
	})
	void test_create_daily_limit_error(
			String account,
			String cashInFrom,
			int amount,
			String particular, 
			String message) {
		var form = new CashInForm(account, cashInFrom, amount, particular);
		
		var exception = assertThrows(BusinessException.class, () -> service.cashIn(form));
		
		assertEquals(message, exception.getMessage());
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource(value = {
			",MPU,100000,Test Cash In,Please enter login user id.",	
			"kyaw@gmail.com,,100000,Test Cash In,Please select cash in from.",	
			"kyaw@gmail.com,MPU,,Test Cash In,Please enter cash in amount.",	
		})
	void test_create_constraint_error(
			String account,
			String cashInFrom,
			Integer amount,
			String particular, 
			String message) {
		var form = new CashInForm(account, cashInFrom, amount, particular);
		
		var exception = assertThrows(ValidationException.class, () -> service.cashIn(form));
		
		assertEquals(message, exception.getMessage());
	}
	
	@Order(5)
	void test_success() {
		
	}
	
	
}
