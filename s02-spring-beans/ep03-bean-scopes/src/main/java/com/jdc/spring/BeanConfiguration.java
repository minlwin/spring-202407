package com.jdc.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.jdc.spring.bean.Container;
import com.jdc.spring.bean.ContainerBean;

@Configuration
public class BeanConfiguration {

	@Bean
	@Scope("prototype")
	Container prototypeBean() {
		return new ContainerBean();
	}
	
	@Bean
	@Scope("singleton")
	Container singletonBean() {
		return new ContainerBean();
	}
}
