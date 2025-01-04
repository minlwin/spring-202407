package com.jdc.shop.controller.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.account.entity.Account_;
import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.entity.Customer.Gender;
import com.jdc.shop.model.account.entity.Customer_;

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
		cq.multiselect(
			root.get(Customer_.id),
			root.get(Customer_.name),
			root.get(Customer_.phone),
			root.get(Customer_.account).get(Account_.email),
			root.get(Customer_.dob),
			root.get(Customer_.gender),
			root.get(Customer_.registeredAt)
		);
	}
}
