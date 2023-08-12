package com.store.backend.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.store.backend.api.model.RegistrationBody;
import com.store.backend.exception.UserAlreadyExistsException;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private AuthenticationService userService;


  @Test
  @Transactional
  public void testRegisterUser() {
    RegistrationBody body = new RegistrationBody();
    body.setUsername("UserA");
    body.setEmail("UserServiceTest$testRegisterUser@junit.com");
    body.setFirstName("FirstName");
    body.setLastName("LastName");
    body.setPassword("MySecretPassword123");
    Assertions.assertThrows(UserAlreadyExistsException.class,
        () -> userService.registerUser(body), "Username should already be in use.");
    body.setUsername("UserServiceTest$testRegisterUser");

  }

}