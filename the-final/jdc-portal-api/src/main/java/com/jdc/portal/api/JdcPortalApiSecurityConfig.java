package com.jdc.portal.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.jdc.portal.api.utils.exceptions.SecurityExceptionHandler;
import com.jdc.portal.api.utils.security.AppTokenFilter;
import com.jdc.portal.api.utils.security.AppTokenProvider;

@Configuration
@EnableJpaAuditing
@PropertySource("classpath:/token.properties")
public class JdcPortalApiSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityExceptionHandler securityExceptionHandler) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {});
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/anonymous/**").permitAll();
			req.requestMatchers("/student/**").hasRole("STUDENT");
			req.requestMatchers("/office/**").hasRole("OFFICE");
			req.requestMatchers("/teacher/**").hasRole("TEACHER");
			req.anyRequest().denyAll();
		});
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterAfter(appTokenFilter(), ExceptionTranslationFilter.class);
		
		http.exceptionHandling(excption -> {
			excption.accessDeniedHandler(securityExceptionHandler);
		});
		
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	RoleHierarchy roleHierarchy() {
		return RoleHierarchyImpl.fromHierarchy("""
				ROLE_ADMIN > ROLE_STUDENT
				ROLE_ADMIN > ROLE_MANAGEMENT
				ROLE_MANAGEMENT > ROLE_OFFICE
				ROLE_MANAGEMENT > ROLE_TEACHER
				""");
	}
	
	@Bean
	AppTokenFilter appTokenFilter() {
		return new AppTokenFilter(appTokenProvider());
	}
	
	@Bean
	AppTokenProvider appTokenProvider() {
		return new AppTokenProvider();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityExceptionHandler securityExceptionHandler() {
		return new SecurityExceptionHandler();
	}
}
