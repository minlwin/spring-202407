package com.jdc.shop.controller.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.entity.Customer.Gender;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CustomerInfo(
		UUID id,
		String name,
		String phone,
		String email,
		LocalDate dob,
		Gender gender,
		LocalDateTime registeredAt) {

	public static void select(CriteriaQuery<CustomerInfo> cq, Root<Customer> root) {
		
	}
}
