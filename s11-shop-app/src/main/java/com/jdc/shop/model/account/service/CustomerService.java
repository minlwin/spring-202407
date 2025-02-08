package com.jdc.shop.model.account.service;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.CustomerSearch;
import com.jdc.shop.controller.input.SignUpForm;
import com.jdc.shop.controller.output.CustomerDetails;
import com.jdc.shop.controller.output.CustomerInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.account.entity.Account;
import com.jdc.shop.model.account.entity.Account.Role;
import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.entity.Customer_;
import com.jdc.shop.model.account.repo.AccountRepo;
import com.jdc.shop.model.account.repo.CustomerRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements SignUpService, CustomerReferenceService{
	
	private final PasswordEncoder passwordEncoder;
	private final AccountRepo accountRepo;
	private final CustomerRepo customerRepo;
	
	@Transactional
	public Authentication signUp(SignUpForm signUpForm) {
		
		var account = new Account();
		account.setEmail(signUpForm.getEmail());
		account.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
		account.setRole(Role.Customer);
		account = accountRepo.save(account);
		
		var customer = new Customer();
		customer.setAccount(account);
		customer.setName(signUpForm.getName());
		customer.setPhone(signUpForm.getPhone());
		customer.setRegisteredAt(LocalDateTime.now());
		
		customerRepo.save(customer);
		
		return UsernamePasswordAuthenticationToken.unauthenticated(signUpForm.getEmail(), signUpForm.getPassword());
	}

	@Override
	@PreAuthorize(value = "hasAuthority('Admin')")
	public PageInfo<CustomerInfo> search(CustomerSearch search, int page, int size) {
		return customerRepo.search(queryFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<CustomerInfo>> queryFunc(CustomerSearch search) {
		return cb -> {
			var cq = cb.createQuery(CustomerInfo.class);
			var root = cq.from(Customer.class);
			
			CustomerInfo.select(cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Customer_.registeredAt)));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(CustomerSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Customer.class);
			cq.select(cb.count(root.get(Customer_.id)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	@Override
	public CustomerDetails findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
