package com.jdc.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/", "/login", "/resources/**").permitAll();
			req.requestMatchers("/admin").hasAuthority("Admin");
			req.requestMatchers("/member").hasAnyAuthority("Admin", "Member");
		});
		
		http.formLogin(form -> {
			form.loginPage("/login").loginProcessingUrl("/login");
		});
		
		http.logout(logout -> {
			logout.logoutSuccessUrl("/");
		});
		
		http.exceptionHandling(exception -> {
			exception.accessDeniedPage("/forbidden");
		});
		
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
			User.withUsername("admin").authorities("Admin").password("{noop}admin").build(),
			User.withUsername("member").authorities("Member").password("{noop}member").build()
		);
	}
	
	@Bean
	DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		return new DefaultWebSecurityExpressionHandler();
	}
}
