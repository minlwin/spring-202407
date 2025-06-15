package com.jdc.portal.api.utils.schedules;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.portal.api.controller.authenticated.input.ChangePasswordRequest;
import com.jdc.portal.api.controller.authenticated.output.ChangePasswordResponse;
import com.jdc.portal.api.model.repo.AccountRepo;
import com.jdc.portal.api.utils.cipher.PasswordCipherUtils;
import com.jdc.portal.api.utils.exceptions.ApiBusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

	private final AccountRepo accountRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public ChangePasswordResponse execute(ChangePasswordRequest req) {
		
		var oldPassword = PasswordCipherUtils.decrypt(req.oldPassword());
		var newPassword = PasswordCipherUtils.decrypt(req.newPassword());
		
		var account = accountRepo.findById(req.email()).get();
		
		if(!passwordEncoder.matches(oldPassword, account.getPassword())) {
			throw new ApiBusinessException("Please check your old password.");
		}
		
		account.setPassword(passwordEncoder.encode(newPassword));
		account.setChangedPass(true);
		account.setChangePassAt(LocalDateTime.now());
		
		return new ChangePasswordResponse("Your password has changed successfully.");
	}
}
