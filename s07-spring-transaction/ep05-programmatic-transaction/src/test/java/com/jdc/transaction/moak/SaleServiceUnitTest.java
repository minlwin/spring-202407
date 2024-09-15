package com.jdc.transaction.moak;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.PlatformTransactionManager;

import com.jdc.transaction.exceptions.BusinessException;
import com.jdc.transaction.repo.MemberRepo;
import com.jdc.transaction.repo.ProductRepo;
import com.jdc.transaction.repo.SaleHistoryRepo;
import com.jdc.transaction.repo.SaleItemRepo;
import com.jdc.transaction.service.SaleServiceImpl;
import com.jdc.transaction.service.model.ProductInfo;
import com.jdc.transaction.service.model.SaleForm;
import com.jdc.transaction.service.model.SaleItem;
import com.jdc.transaction.service.model.SaleResult;

@TestMethodOrder(value = OrderAnnotation.class)
@ExtendWith(value = MockitoExtension.class)
public class SaleServiceUnitTest {

	@Mock private MemberRepo memberRepo;
	@Mock private ProductRepo productRepo;
	@Mock private SaleHistoryRepo saleHistoryRepo;
	@Mock private SaleItemRepo saleItemRepo;
	@Mock private PlatformTransactionManager transactionManager;
	
	@InjectMocks
	private SaleServiceImpl service;
	
	@Order(1)
	@ParameterizedTest
	@NullSource
	void test_null_form(SaleForm form) {
		
		var ex = assertThrows(BusinessException.class, () -> service.checkOut(form));
		
		assertThat(ex)
			.hasFieldOrPropertyWithValue("message", "Sale form must not be null.");
	}
	
	@Order(2)
	@ParameterizedTest
	@MethodSource
	void test_invalid_member_id(SaleForm form) {
		when(memberRepo.countById(form.memberId()))
			.thenReturn(0L);
		
		var ex = assertThrows(BusinessException.class, () -> service.checkOut(form));
		
		assertThat(ex)
			.hasFieldOrPropertyWithValue("message", "Invalid member id.");
		
	}
	
	static List<SaleForm> test_invalid_member_id() {
		return List.of(new SaleForm(1, List.of()));
	}
	
	@Order(3)
	@ParameterizedTest
	@MethodSource
	void test_empty_items(SaleForm form) {
		when(memberRepo.countById(form.memberId()))
			.thenReturn(1L);
		
		var ex = assertThrows(BusinessException.class, () -> service.checkOut(form));
		
		assertThat(ex)
			.hasFieldOrPropertyWithValue("message", "Please select items.");
	}

	static List<SaleForm> test_empty_items() {
		return List.of(new SaleForm(1, List.of()), new SaleForm(1, null));
	}
	
	@Order(4)
	@ParameterizedTest
	@MethodSource
	void test_invalid_product_id(SaleForm form) {
		when(memberRepo.countById(form.memberId()))
			.thenReturn(1L);
		
		for(var item: form.items()) {
			when(productRepo.findById(item.productId()))
				.thenReturn(Optional.empty());
		}
		
		when(saleHistoryRepo.create(form.memberId()))
			.thenReturn(1);
		
		var result = service.checkOut(form);
		
		assertThat(result)
			.extracting(SaleResult::id).isEqualTo(1);
		assertThat(result)
			.extracting(SaleResult::status).isEqualTo(SaleResult.Status.Error);
		assertThat(result)
			.extracting(SaleResult::message).isEqualTo("Invalid product id.");
		
	}
	
	static List<SaleForm> test_invalid_product_id() {
		return List.of(new SaleForm(1, List.of(new SaleItem(1, 3))));
	}

	@Order(5)
	@ParameterizedTest
	@MethodSource
	void test_invalid_quantity(SaleForm form) {
		when(memberRepo.countById(form.memberId()))
			.thenReturn(1L);
		
		for(var item: form.items()) {
			when(productRepo.findById(item.productId()))
				.thenReturn(Optional.of(new ProductInfo(
						item.productId(), 
						"Product %s".formatted(item.productId()), 
						1000, 1, "Category")));
		}
		
		when(saleHistoryRepo.create(form.memberId()))
			.thenReturn(1);

		var result = service.checkOut(form);
		
		assertThat(result)
			.extracting(SaleResult::id).isEqualTo(1);
		assertThat(result)
			.extracting(SaleResult::status).isEqualTo(SaleResult.Status.Error);
		assertThat(result)
			.extracting(SaleResult::message).isEqualTo("Please enter quentity.");
		
	}
	
	static List<SaleForm> test_invalid_quantity() {
		return List.of(new SaleForm(1, List.of(new SaleItem(1, 0))));
	}
	
	
	@Order(6)
	@ParameterizedTest
	@MethodSource
	void test_success(SaleForm form) {
		when(memberRepo.countById(form.memberId()))
			.thenReturn(1L);
	
	for(var item: form.items()) {
		when(productRepo.findById(item.productId()))
			.thenReturn(Optional.of(new ProductInfo(
					item.productId(), 
					"Product %s".formatted(item.productId()), 
					1000, 1, "Category")));
	}
	
	when(saleHistoryRepo.create(form.memberId()))
		.thenReturn(1);

	var result = service.checkOut(form);
	
	assertThat(result)
		.extracting(SaleResult::id).isEqualTo(1);
	assertThat(result)
		.extracting(SaleResult::status).isEqualTo(SaleResult.Status.Success);
	assertThat(result)
		.extracting(SaleResult::message).isEqualTo("Check out operation is done successfully.");
	}
	
	static List<SaleForm> test_success() {
		return List.of(new SaleForm(1, List.of(new SaleItem(1, 3))));
	}
}
