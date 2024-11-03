package com.jdc.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.spring.model.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(
	repositoryBaseClass = BaseRepositoryImpl.class,
	basePackages = "com.jdc.spring.model"
)
public class JpaConfig {

}
