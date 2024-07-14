package com.jdc.spring.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean
	HelloBean helloBean() {
		return new HelloBean();
	}
}
