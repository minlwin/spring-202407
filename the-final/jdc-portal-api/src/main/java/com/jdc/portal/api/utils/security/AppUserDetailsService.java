package com.jdc.portal.api.utils.security;

import java.time.LocalDate;

import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jdc.portal.api.model.entity.Account;
import com.jdc.portal.api.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService{
	
	private final AccountRepo accountRepo;
	private final MessageSource messageSource;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return accountRepo.findById(username)
				.map(a -> User.builder()
						.username(username)
						.password(a.getPassword())
						.roles(getRoles(a))
						.disabled(!a.isActivated())
						.accountExpired(isExpired(a))
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(messageSource.getMessage("app.error.notfound", new String[]{"Account", "login id", username}, null)));
	}

	private boolean isExpired(Account a) {
		if(null != a.getExpiredAt() && LocalDate.now().isAfter(a.getExpiredAt()) ) {
			return true;
		}
		return false;
	}

	private String[] getRoles(Account a) {
		return switch(a.getRole()) {
		case Employee -> new String[] {a.getEmployee().getType().name().toUpperCase()};
		default -> new String[] {a.getRole().name().toUpperCase()};
		};
	}
}
