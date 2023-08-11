package com.store.backend.api.security;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtDecoder {
	public DecodedJWT decode(String token) {
		return JWT.require(Algorithm.HMAC256("secret"))
				.build()
				.verify(token);
	}

}
