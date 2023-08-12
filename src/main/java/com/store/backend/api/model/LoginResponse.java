package com.store.backend.api.model;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse {
	private String jwt;
	
	public String getJwt() {
	return jwt;
	}
	
	public void setJwt(String jwt) {
	this.jwt = jwt;
	}
}
