package com.jdc.shop.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.model.account.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService{
	
	private final AccountRepo accountRepo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findOneByEmail(username)
				.map(account -> User.builder()
						.username(username)
						.password(account.getPassword())
						.authorities(account.getRole().name())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException("There is no account with email %s.".formatted(username)));
	}

}
