package com.jdc.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean(name = {
		"strBuilder",
		"sb",
		"stringBuilder"
	})
	StringBuilder stringBuilder() {
		return new StringBuilder();
	}
}
