package com.jdc.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdc.spring.beans.MyClient;
import com.jdc.spring.beans.MyService;
import com.jdc.spring.beans.MyServiceCustom;
import com.jdc.spring.beans.MyServiceDefault;

@Configuration
public class JavaConfig {

	@Bean
	MyServiceCustom myServiceCustom() {
		return new MyServiceCustom();
	}
	
	@Bean
	MyServiceDefault myServiceDefault() {
		return new MyServiceDefault();
	}
	
	@Bean
	MyService myService() {
		return new MyServiceCustom();
	}
	
	@Bean
	MyClient myClient() {
		return new MyClient();
	}
}
