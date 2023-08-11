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
import com.store.backend.api.security.JWTIssuer;
import com.store.backend.exception.UserAlreadyExistsException;
import com.store.backend.model.LocalUser;
import com.store.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private UserService userService;
  private JWTIssuer jwtToken;
  
  public AuthenticationController(UserService userService, JWTIssuer jwtIssuer){
	  this.jwtToken = jwtIssuer;
	  this.userService = userService;
  }
  
  
  
  @PostMapping("/register")
  public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
    try {
      userService.registerUser(registrationBody);
      return ResponseEntity.ok().build();
    } catch (UserAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
  
  
  //previously returned type ResponseEntity<LoginResponse>
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
	  String jwt = jwtToken.issue(1L, "hummus");
	  LoginResponse response = new LoginResponse();
	  response.setJwt(jwt);
	  return ResponseEntity.ok(response);
//    String jwt = userService.loginUser(loginBody);
//    if (jwt == null) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    } else {
//      LoginResponse response = new LoginResponse();
//      response.setJwt(jwt);
//      return ResponseEntity.ok(response);
//    }
  }
  
  @GetMapping("/me")
  public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
    return user;
  }

}