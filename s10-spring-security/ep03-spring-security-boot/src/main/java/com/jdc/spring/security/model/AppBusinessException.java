package com.jdc.spring.security.model;

public class AppBusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AppBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppBusinessException(String message) {
		super(message);
	}
	
}
