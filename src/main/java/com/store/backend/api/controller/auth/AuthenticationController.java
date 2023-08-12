package com.store.backend.api.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.backend.api.model.LoginBody;
import com.store.backend.api.model.LoginResponse;
import com.store.backend.api.model.RegistrationBody;
import com.store.backend.exception.UserAlreadyExistsException;
import com.store.backend.model.LocalUser;
import com.store.backend.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private AuthenticationService authenticationService;
  
  public AuthenticationController(AuthenticationService authenticationService){
	  this.authenticationService = authenticationService;
  }
  
  
  
  @PostMapping("/register")
  public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
    try {
      authenticationService.registerUser(registrationBody);
      return ResponseEntity.ok().build();
    } catch (UserAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
  
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
	  LoginResponse response = authenticationService.loginUser(loginBody);
	  return ResponseEntity.ok(response);
  }

}