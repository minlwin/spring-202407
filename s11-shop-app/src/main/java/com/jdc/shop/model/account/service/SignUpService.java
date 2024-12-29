package com.jdc.shop.model.account.service;

import org.springframework.security.core.Authentication;

import com.jdc.shop.controller.input.SignUpForm;

public interface SignUpService {

	Authentication signUp(SignUpForm signUpForm);
}
