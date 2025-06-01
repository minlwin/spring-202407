package com.jdc.portal.api.utils.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class AppTokenProvider {
	
	public enum Type {
		Access, Refresh
	}
	
	@Value("${app.token.issuer}")
	private String issuer;
	@Value("${app.token.life.access}")
	private int accessLife;
	@Value("${app.token.life.refresh}")
	private int refreshLife;
	
	private static final String roleKey = "rol";
	
	private static SecretKey secretKey = Jwts.SIG.HS512.key().build();

	public Authentication parseAccessToken(String token) {
		return parse(Type.Access, token);
	}

	public Authentication parseRefreshToken(String token) {
		return parse(Type.Refresh, token);
	}

	public String generateAccessToken(Authentication authentication) {
		return generate(Type.Access, authentication);
	}

	public String generateRefreshToken(Authentication authentication) {
		return generate(Type.Refresh, authentication);
	}

	private Authentication parse(Type type, String token) {
		
		var jwt = Jwts.parser()
				.requireIssuer(getIssuer(type))
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
		
		var username = jwt.getPayload().getSubject();
		var roleValue = jwt.getPayload().get(roleKey).toString();
		
		return UsernamePasswordAuthenticationToken.authenticated(
				username, 
				null, 
				Arrays.stream(roleValue.split(","))
					.map(a -> new SimpleGrantedAuthority(a))
					.toList());
	}

	private String generate(Type type, Authentication authentication) {
		
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.SECOND, type == Type.Access ? accessLife : refreshLife);
		var expiration = calendar.getTime();
		
		var roles = authentication.getAuthorities().stream()
				.map(a -> a.getAuthority())
				.collect(Collectors.joining(","));
		
		return Jwts.builder()
				.subject(authentication.getName())
				.issuer(getIssuer(type))
				.issuedAt(issueAt)
				.expiration(expiration)
				.claim(roleKey, roles)
				.signWith(secretKey)
				.compact();
	}
	
	private String getIssuer(Type type) {
		return "%s-%s".formatted(issuer, type);
	}
}
