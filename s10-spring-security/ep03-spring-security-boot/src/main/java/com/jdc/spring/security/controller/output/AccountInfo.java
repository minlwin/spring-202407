package com.jdc.spring.security.controller.output;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.spring.security.model.entity.Account;
import com.jdc.spring.security.model.entity.Account_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record AccountInfo(
		UUID id,
		String name,
		String email,
		boolean activated,
		LocalDateTime requestedAt,
		LocalDateTime activatedAt) {

	public static void select(CriteriaQuery<AccountInfo> cq, Root<Account> root) {
		cq.multiselect(
			root.get(Account_.id),
			root.get(Account_.name),
			root.get(Account_.email),
			root.get(Account_.activated),
			root.get(Account_.requestedAt),
			root.get(Account_.activatedAt)
		);
	}
}
