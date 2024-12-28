package com.jdc.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ShopAppSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// Authorization
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/resources/**", "/public/**").permitAll();
			req.requestMatchers("/admin/**").hasAuthority("Admin");
			req.anyRequest().authenticated();
		});
		
		// Login Form
		http.formLogin(login -> {
			
		});
		
		// Logout
		http.logout(logout -> {
		});
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
