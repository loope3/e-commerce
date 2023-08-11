package com.store.backend.api.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
	private String jwt;
	
	public String getJwt() {
	return jwt;
	}
	
	public void setJwt(String jwt) {
	this.jwt = jwt;
	}
}
