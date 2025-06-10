package com.jdc.portal.api.controller.anonymous.output;

public record AccessTokenResponse(
		String name,
		String role,
		String accessToken,
		String refreshToken) {

}
