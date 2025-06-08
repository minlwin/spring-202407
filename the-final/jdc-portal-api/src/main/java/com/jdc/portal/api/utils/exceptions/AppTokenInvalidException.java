package com.jdc.portal.api.utils.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.jdc.portal.api.utils.security.AppTokenProvider.Type;

import io.jsonwebtoken.JwtException;

public class AppTokenInvalidException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	private Type type;

	public AppTokenInvalidException(String msg, JwtException cause, Type tokenType) {
		super(msg, cause);
	}

	public Type getType() {
		return type;
	}
}
