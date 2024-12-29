package com.jdc.shop.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.admin")
public class AppAdminUserProperties {

	private String username;
	private String password;
}
