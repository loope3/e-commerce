package com.store.backend.api.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Long userId;     
    private final String username;
    
    @JsonIgnore
    private final String password;
    

    
	public UserPrincipal(Long userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  
    }

    @Override
    public boolean isEnabled() {
        return true; 
    }

	public Long getUserId() {
		return userId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


}