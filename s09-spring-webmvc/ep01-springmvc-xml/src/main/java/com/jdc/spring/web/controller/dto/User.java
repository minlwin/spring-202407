package com.jdc.spring.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String name;
	private String phone;
	private String email;
}
