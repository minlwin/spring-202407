package com.jdc.shop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.model.account.entity.Account;
import com.jdc.shop.model.account.entity.Account.Role;
import com.jdc.shop.model.account.repo.AccountRepo;
import com.jdc.shop.setting.AppAdminUserProperties;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminUserInitializer {
	
	private final AccountRepo accountRepo;
	private final AppAdminUserProperties props;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@EventListener(value = ContextRefreshedEvent.class)
	public void initialize() {
		if(accountRepo.count() == 0L) {
			var admin = new Account();
			admin.setEmail(props.getUsername());
			admin.setPassword(passwordEncoder.encode(props.getPassword()));
			admin.setRole(Role.Admin);
			accountRepo.save(admin);
		}
	}
}
