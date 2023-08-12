package com.store.backend.service;

	
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.store.backend.api.model.LoginBody;
import com.store.backend.api.model.LoginResponse;
import com.store.backend.api.model.RegistrationBody;
import com.store.backend.api.security.CustomPasswordEncoder;
import com.store.backend.api.security.JWTIssuer;
import com.store.backend.api.security.UserPrincipal;
import com.store.backend.exception.UserAlreadyExistsException;
import com.store.backend.model.LocalUser;
import com.store.backend.model.dao.LocalUserDAO;

@Service
public class AuthenticationService {

  private LocalUserDAO localUserDAO;
  private final AuthenticationManager authenticationManager;
  private final LoginResponse loginResponse;
  private final JWTIssuer jwtIssuer;
  private final CustomPasswordEncoder passwordEncoder;

  public AuthenticationService(LocalUserDAO localUserDAO, AuthenticationManager authenticationManager, 
		             CustomPasswordEncoder passwordEncoder, JWTIssuer jwtIssuer, LoginResponse loginResponse) {
    this.localUserDAO = localUserDAO;
    this.jwtIssuer = jwtIssuer;
    this.authenticationManager = authenticationManager;
    this.loginResponse = loginResponse;
    this.passwordEncoder = passwordEncoder ;
  }

  public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
    if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
      throw new UserAlreadyExistsException();
    }
    LocalUser user = new LocalUser();
    user.setEmail(registrationBody.getEmail());
    user.setUsername(registrationBody.getUsername());
    user.setFirstName(registrationBody.getFirstName());
    user.setLastName(registrationBody.getLastName());
    user.setPassword(passwordEncoder.passwordEncoder().encode(registrationBody.getPassword()));
    return localUserDAO.save(user);
  }

  public LoginResponse loginUser(LoginBody loginBody) {
	  var authentication = authenticationManager.authenticate(
			  	new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword())
			  );
	  SecurityContextHolder.getContext().setAuthentication(authentication);
	  var principal = (UserPrincipal) authentication.getPrincipal();
	  var token =  jwtIssuer.issue(principal.getUserId(), principal.getUsername());
	  loginResponse.setJwt(token);
	  return loginResponse;
  }

}