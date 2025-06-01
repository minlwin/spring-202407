package com.jdc.portal.api.controller.output;

public record AccessTokenResponse(
		String name,
		String role,
		String accessToken,
		String refreshToken) {

}
