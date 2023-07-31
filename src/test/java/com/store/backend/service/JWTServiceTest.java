package com.store.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.store.backend.model.LocalUser;
import com.store.backend.model.dao.LocalUserDAO;

@SpringBootTest
public class JWTServiceTest {

  @Autowired
  private JWTService jwtService;
  
  @Autowired
  private LocalUserDAO localUserDAO;

  @Test
  public void testAuthTokenReturnsUsername() {
    LocalUser user = localUserDAO.findByUsernameIgnoreCase("UserA").get();
    String token = jwtService.generateJWT(user);
    Assertions.assertEquals(user.getUsername(), jwtService.getUsername(token), "Token for auth should contain users username.");
  }

}