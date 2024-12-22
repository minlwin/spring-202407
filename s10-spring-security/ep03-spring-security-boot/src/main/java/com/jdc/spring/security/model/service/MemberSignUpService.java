package com.jdc.spring.security.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.security.controller.input.SignUpForm;
import com.jdc.spring.security.model.AppBusinessException;
import com.jdc.spring.security.model.entity.Account;
import com.jdc.spring.security.model.entity.Account.Role;
import com.jdc.spring.security.model.repo.AccountRepo;

@Service
public class MemberSignUpService {
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Transactional
	public void signUp(SignUpForm form) {
		
		// Check Email Address
		if(repo.findOneByEmail(form.getEmail()).isPresent()) {
			throw new AppBusinessException("Your email has aready been used. Please use another email.");
		}
		
		var entity = new Account();
		entity.setName(form.getName());
		entity.setEmail(form.getEmail());
		entity.setRole(Role.Member);
		entity.setPassword(encoder.encode(form.getPassword()));
		entity.setRequestedAt(LocalDateTime.now());
		
		repo.save(entity);
	}

}
