package com.jdc.portal.api.utils.exceptions;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.portal.api.utils.security.AppTokenProvider.Type;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

 	private final MessageSource messageSource;
  
	@ExceptionHandler({
		ApiBusinessException.class,
		ApiValidationException.class
	})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	List<String> handle(ApiBusinessException e) {
		return e.getMessages();
	}
	
	@ExceptionHandler
	ResponseEntity<List<String>> handle(AppTokenInvalidException e) {
		if(e.getCause() instanceof ExpiredJwtException) {
			if(e.getType() == Type.Access) {
				return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(List.of("Expired access token."));
			} 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(List.of("Expired refresh token."));
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(List.of("Invalid %s token.".formatted(e.getType())));
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	List<String> handle(ApiEmploeeNotChangePasswordException e) {
		return List.of(messageSource.getMessage(e.getMessage(), new Object[] {}, null));
	}
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	List<String> handle(AuthenticationException e) {
		return switch(e) {
		case UsernameNotFoundException une -> List.of(une.getMessage());
		case BadCredentialsException bce -> List.of(messageSource.getMessage("app.auth.error.badcredential", null, null));
		case DisabledException dsa -> List.of(messageSource.getMessage("app.auth.error.disabled", null, null));
		case AccountExpiredException ace -> List.of(messageSource.getMessage("app.auth.error.expired", null, null));
		default -> List.of(messageSource.getMessage("app.auth.error.others", null, null));
		};
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	List<String> handle(AccessDeniedException e) {
		return List.of(messageSource.getMessage("app.forbidden.error", null, null));
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	List<String> handle(RuntimeException e) {
		e.printStackTrace();
		return List.of("System Error");
	}
}
