package com.jdc.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jdc.spring.model")
public class RootConfiguration {

	@Bean
	DataSource dataSource() {
		var bean = new HikariDataSource();
		bean.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bean.setJdbcUrl("jdbc:mysql://localhost:3306/location_db");
		bean.setUsername("spring");
		bean.setPassword("spring");
		return bean;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.jdc.spring.model.entity");
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		bean.setJpaProperties(getJpaProperties());
		return bean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Properties getJpaProperties() {
		var prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto", "create");
		prop.put("hibernate.show_sql", true);
		prop.put("hibernate.format_sql", true);
		return prop;
	}
}
