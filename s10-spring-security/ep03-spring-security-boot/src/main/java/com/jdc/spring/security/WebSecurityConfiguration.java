package com.jdc.spring.security;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jdc.spring.security.model.service.AdminUserInitializer;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(req -> {
			req.dispatcherTypeMatchers(DispatcherType.INCLUDE, DispatcherType.FORWARD, DispatcherType.ERROR)
				.permitAll();
			req.requestMatchers("/", "/login", "/resources/**").permitAll();
		});
		
		http.formLogin(form -> {
			form.loginPage("/login");
			form.loginProcessingUrl("/login");
		});
		
		http.logout(logout -> {
			logout.logoutSuccessUrl("/");
		});
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	ApplicationRunner applicationRunner(AdminUserInitializer initializer) {
		return args -> initializer.initialize();
	}
}
