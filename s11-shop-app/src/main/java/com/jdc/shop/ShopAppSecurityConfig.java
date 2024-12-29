package com.jdc.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.DispatcherType;

@Configuration
public class ShopAppSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// Authorization
		http.authorizeHttpRequests(req -> {
			req.dispatcherTypeMatchers(DispatcherType.INCLUDE, DispatcherType.FORWARD, DispatcherType.ERROR)
				.permitAll();
			req.requestMatchers("/resources/**", "/public/**", "/").permitAll();
			req.requestMatchers("/admin/**").hasAuthority("Admin");
			req.anyRequest().authenticated();
		});
		
		// Login Form
		http.formLogin(login -> {
			login.loginPage("/public/signin");
			login.defaultSuccessUrl("/");
		});
		
		// Logout
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
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}
}
