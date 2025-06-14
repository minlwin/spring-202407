package com.jdc.portal.api.controller.authenticated;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.portal.api.controller.authenticated.input.ChangePasswordRequest;
import com.jdc.portal.api.controller.authenticated.output.ChangePasswordResponse;

@RestController
@RequestMapping("authenticated/change-password")
public class ChangePasswordApi {

	@PostMapping
	ChangePasswordResponse changePassword(
			@Validated @RequestBody ChangePasswordRequest request, BindingResult bindingResult) {
		return null;
	}
}
