package com.jdc.portal.api.controller.authenticated.input;

import com.jdc.portal.api.utils.validation.Required;

public record ChangePasswordRequest(
		@Required(name = "Email")
		String email,
		@Required(name = "Old Password")
		String oldPassword,
		@Required(name = "New Password")
		String newPassword) {

}
