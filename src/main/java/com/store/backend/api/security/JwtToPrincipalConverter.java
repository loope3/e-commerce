package com.store.backend.api.security;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtToPrincipalConverter {
	public UserPrincipal convert(DecodedJWT jwt) {
		return new UserPrincipal(Long.valueOf(jwt.getSubject()), jwt.getClaim("USERNAME").asString());
	}
}
