package com.store.backend.api.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTIssuer {
	
	  @Value("${jwt.algorithm.key}")
	  private String algorithmKey;

	  @Value("${jwt.issuer}")
	  private String issuer;

	  @Value("${jwt.expiryInSeconds}")
	  private int expiryInSeconds;
	  
	  private static final String USERNAME_KEY = "USERNAME";

	  
	public String issue(Long userId, String username) {
		return JWT.create()
				.withSubject(String.valueOf(userId))
			    .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
			    .withClaim(USERNAME_KEY, username)
				.sign(Algorithm.HMAC256("secret"));	
	}
	
 
}
