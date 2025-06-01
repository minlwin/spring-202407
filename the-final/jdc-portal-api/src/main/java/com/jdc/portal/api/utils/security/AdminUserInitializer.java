package com.jdc.portal.api.utils.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.portal.api.model.entity.Account;
import com.jdc.portal.api.model.entity.Account.Role;
import com.jdc.portal.api.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:/admin.properties")
public class AdminUserInitializer implements ApplicationRunner {

	private final AccountRepo accountRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Value("${app.admin.username}")
	private String username;
	@Value("${app.admin.password}")
	private String password;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		if(accountRepo.count() == 0L) {
			var admin = new Account();
			admin.setEmail(username);
			admin.setPassword(passwordEncoder.encode(password));
			admin.setRole(Role.Admin);

			admin.setRegisteredAt(LocalDateTime.now());
			admin.setActivatedAt(LocalDateTime.now());
			admin.setActivated(true);
			
			accountRepo.persist(admin);
		}
	}

}
