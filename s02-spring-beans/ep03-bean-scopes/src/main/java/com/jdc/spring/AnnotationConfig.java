package com.jdc.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.jdc.spring.bean")
public class AnnotationConfig {

}
