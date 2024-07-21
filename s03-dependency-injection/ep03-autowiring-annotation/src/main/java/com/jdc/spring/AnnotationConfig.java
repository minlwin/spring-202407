package com.jdc.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jdc.spring.beans.MyService;
import com.jdc.spring.beans.MyServiceCustom;

@Configuration
@ComponentScan("com.jdc.spring.beans")
public class AnnotationConfig {

	@Bean
	MyService myService() {
		return new MyServiceCustom();
	}
}
