package com.jdc.spring.trx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
