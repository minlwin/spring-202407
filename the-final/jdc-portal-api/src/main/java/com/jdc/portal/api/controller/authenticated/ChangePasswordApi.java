package com.jdc.portal.api.controller.authenticated;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.portal.api.controller.authenticated.input.ChangePasswordRequest;
import com.jdc.portal.api.controller.authenticated.output.ChangePasswordResponse;
import com.jdc.portal.api.utils.schedules.ChangePasswordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("authenticated/change-password")
public class ChangePasswordApi {

	private final ChangePasswordService service;
	
	@PostMapping
	@PreAuthorize("authentication.name eq #request.email")
	ChangePasswordResponse changePassword(
			@Validated @RequestBody ChangePasswordRequest request, BindingResult bindingResult) {
		return service.execute(request);
	}
}
