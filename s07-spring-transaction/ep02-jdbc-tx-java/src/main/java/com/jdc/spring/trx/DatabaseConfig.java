package com.jdc.spring.trx;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(
	basePackages = "com.jdc.spring.trx"
)
@EnableTransactionManagement
@PropertySource("classpath:/database.properties")
public class DatabaseConfig {
	
	@Value("${app.datasource.url}")
	private String url;
	@Value("${app.datasource.username}")
	private String username;
	@Value("${app.datasource.password}")
	private String password;
	@Value("${app.datasource.maximum-pool-size}")
	private int maxPoolSize;
	
	@Value("${app.sql.schema}")
	private String schemaSql;
	@Value("${app.sql.limit-setting}")
	private String limitSettingSql;
	@Value("${app.sql.account}")
	private String accountSql;

	// DataSource Configuration
	@Bean
	DataSource dataSource() {
		var config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setMaximumPoolSize(maxPoolSize);
		return new HikariDataSource(config);
	}
	
	// Database Initialization Configuration
	@Bean
	DataSourceInitializer dataSourceInitializer() {
		var bean = new DataSourceInitializer();
		bean.setDataSource(dataSource());
		var initializer = new ResourceDatabasePopulator();
		
		var schema = new ClassPathResource(schemaSql);
		var limitSettings = new ClassPathResource(limitSettingSql);
		var accounts = new ClassPathResource(accountSql);
		
		initializer.setScripts(schema, limitSettings, accounts);
		
		return bean;
	}
	
	// Transaction Manager Configuration
	@Bean
	TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
