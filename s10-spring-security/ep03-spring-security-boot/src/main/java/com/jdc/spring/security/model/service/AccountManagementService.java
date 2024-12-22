package com.jdc.spring.security.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.security.controller.input.AccountSearch;
import com.jdc.spring.security.controller.output.AccountInfo;
import com.jdc.spring.security.model.entity.Account;
import com.jdc.spring.security.model.entity.Account_;
import com.jdc.spring.security.model.repo.AccountRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class AccountManagementService {

	@Autowired
	private AccountRepo repo;

	@Transactional(readOnly = true)
	public List<AccountInfo> search(AccountSearch search) {
		return repo.search(queryFunc(search));
	}

	private Function<CriteriaBuilder, CriteriaQuery<AccountInfo>> queryFunc(AccountSearch search) {
		return cb -> {
			var cq = cb.createQuery(AccountInfo.class);
			var root = cq.from(Account.class);
			
			AccountInfo.select(cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Account_.requestedAt)));
			return cq;
		};
	}
}
