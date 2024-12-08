package com.jdc.spring.security.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.security.model.entity.Account;
import com.jdc.spring.security.model.entity.Account.Role;
import com.jdc.spring.security.model.repo.AccountRepo;

@Service
public class AdminUserInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountRepo accountRepo;
	
	@Transactional
	public void initialize() {
		
		if(accountRepo.count() == 0) {
			
			var admin = new Account();
			admin.setName("Admin User");
			admin.setRole(Role.Admin);
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("admin"));

			admin.setActivated(true);
			admin.setActivatedAt(LocalDateTime.now());
			admin.setRequestedAt(LocalDateTime.now());
			
			accountRepo.save(admin);
		}
	}
}
