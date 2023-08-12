package com.store.backend.api.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.store.backend.model.LocalUser;
import com.store.backend.model.dao.LocalUserDAO;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	private final LocalUserDAO localUserDAO;
	
	public CustomUserDetailsService(LocalUserDAO localUserDAO) {
		this.localUserDAO = localUserDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
		LocalUser user = localUserDAO.findByUsernameIgnoreCase(username).get();

		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword());
	}

}
