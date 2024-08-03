package com.jdc.spring;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.jdc.spring.pool")
public class ApplicationConfig {

	@Bean
	DataSource dataSource() {
		var ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/clientdb");
		ds.setUsername("clientdb");
		ds.setPassword("clientdb");
		return ds;
	}
}
