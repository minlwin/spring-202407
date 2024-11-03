package com.jdc.spring.web.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	repositoryBaseClass = BaseRepositoryImpl.class
)
public class BaseRepositoryConfig {

}
