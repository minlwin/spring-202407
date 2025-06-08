package com.jdc.portal.api.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.portal.api.controller.input.AccessTokenRequest;
import com.jdc.portal.api.controller.output.AccessTokenResponse;
import com.jdc.portal.api.model.repo.AccountRepo;
import com.jdc.portal.api.utils.security.AppTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("anonymous/token")
@RequiredArgsConstructor
public class AccessTokenApi {
	
	private final AuthenticationManager authenticationManager;
	private final AppTokenProvider appTokenProvider;
	private final AccountRepo accountRepo;

	@PostMapping("generate")
	public AccessTokenResponse generate(@Validated @RequestBody AccessTokenRequest req, BindingResult bindingResult) {
		var authentication = authenticationManager.authenticate(req.getAuthentication());
		return createResponse(req.username(), authentication);
	}
	
	@PostMapping("refresh")
	public AccessTokenResponse refresh(@Validated @RequestBody AccessTokenRequest req, BindingResult bindingResult) {
		var authentication = appTokenProvider.parseRefreshToken(req.password());
		return createResponse(req.username(), authentication);
	}

	private AccessTokenResponse createResponse(String username, Authentication authentication) {
		var accessToken = appTokenProvider.generateAccessToken(authentication);
		var refreshToken = appTokenProvider.generateRefreshToken(authentication);
		var role = authentication.getAuthorities().stream()
				.map(a -> a.getAuthority()).findAny().orElse("ROLE_ANONYMOUS");
		
		var account = accountRepo.findById(username)
				.orElseThrow();
		
		var name = switch(account.getRole()) {
		case Admin -> "Admin User";
		case Employee -> account.getEmployee().getName();
		case Student -> account.getStudent().getName();
		};
		
		return new AccessTokenResponse(name, role, accessToken, refreshToken);
	}
	
}
