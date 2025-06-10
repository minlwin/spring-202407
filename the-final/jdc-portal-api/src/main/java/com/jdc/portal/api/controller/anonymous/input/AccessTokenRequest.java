package com.jdc.portal.api.controller.anonymous.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.jdc.portal.api.utils.validation.Required;

public record AccessTokenRequest(
		@Required(name = "Email for login")
		String username,
		@Required(name = "Password or Refresh token")
		String password) {

	public Authentication getAuthentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}
}
