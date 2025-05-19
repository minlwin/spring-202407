package com.jdc.portal.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.portal.api.model.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(
	repositoryBaseClass = BaseRepositoryImpl.class
)
public class JdcPortalApiJpaConfig {

}
