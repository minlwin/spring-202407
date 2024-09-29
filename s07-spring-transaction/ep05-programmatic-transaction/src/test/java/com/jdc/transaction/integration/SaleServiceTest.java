package com.jdc.transaction.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.jdc.transaction.exceptions.BusinessException;
import com.jdc.transaction.service.SaleService;
import com.jdc.transaction.service.model.SaleForm;
import com.jdc.transaction.service.model.SaleItem;
import com.jdc.transaction.service.model.SaleResult;

@SpringBootTest
@ActiveProfiles("template")
@TestMethodOrder(value = OrderAnnotation.class)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, scripts = "classpath:/test-data.sql")
public class SaleServiceTest {

	@Autowired
	private SaleService service;

	@Order(1)
	@ParameterizedTest
	@NullSource
	void test_null_form(SaleForm form) {

		var ex = assertThrows(BusinessException.class, () -> service.checkOut(form));

		assertThat(ex).hasFieldOrPropertyWithValue("message", "Sale form must not be null.");
	}

	@Order(2)
	@ParameterizedTest
	@MethodSource
	void test_invalid_member_id(SaleForm form) {

		var ex = assertThrows(BusinessException.class, () -> service.checkOut(form));

		assertThat(ex).hasFieldOrPropertyWithValue("message", "Invalid member id.");
	}

	static List<SaleForm> test_invalid_member_id() {
		return List.of(new SaleForm(6, List.of()));
	}

	@Order(3)
	@ParameterizedTest
	@MethodSource
	void test_empty_items(SaleForm form) {
		var ex = assertThrows(BusinessException.class, () -> service.checkOut(form));

		assertThat(ex).hasFieldOrPropertyWithValue("message", "Please select items.");
	}

	static List<SaleForm> test_empty_items() {
		return List.of(new SaleForm(1, List.of()), new SaleForm(1, null));
	}

	@Order(4)
	@ParameterizedTest
	@MethodSource
	void test_invalid_product_id(SaleForm form) {
		var result = service.checkOut(form);

		assertThat(result).extracting(SaleResult::id).isEqualTo(1);
		assertThat(result).extracting(SaleResult::status).isEqualTo(SaleResult.Status.Error);
		assertThat(result).extracting(SaleResult::message).isEqualTo("Invalid product id.");

	}

	static List<SaleForm> test_invalid_product_id() {
		return List.of(new SaleForm(1, List.of(new SaleItem(5, 3))));
	}

	@Order(5)
	@ParameterizedTest
	@MethodSource
	void test_invalid_quantity(SaleForm form) {

		var result = service.checkOut(form);

		assertThat(result).extracting(SaleResult::id).isEqualTo(2);
		assertThat(result).extracting(SaleResult::status).isEqualTo(SaleResult.Status.Error);
		assertThat(result).extracting(SaleResult::message).isEqualTo("Please enter quentity.");

	}

	static List<SaleForm> test_invalid_quantity() {
		return List.of(new SaleForm(1, List.of(new SaleItem(1, 0))));
	}

	@Order(6)
	@ParameterizedTest
	@MethodSource
	void test_success(SaleForm form) {

		var result = service.checkOut(form);

		assertThat(result).extracting(SaleResult::id).isEqualTo(3);
		assertThat(result).extracting(SaleResult::status).isEqualTo(SaleResult.Status.Success);
		assertThat(result).extracting(SaleResult::message).isEqualTo("Check out operation is done successfully.");
	}

	static List<SaleForm> test_success() {
		return List.of(new SaleForm(1, List.of(new SaleItem(1, 3))));
	}
}
