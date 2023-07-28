package com.store.backend.service;

	
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.backend.api.model.LoginBody;
import com.store.backend.api.model.RegistrationBody;
import com.store.backend.exception.UserAlreadyExistsException;
import com.store.backend.model.LocalUser;
import com.store.backend.model.dao.LocalUserDAO;

@Service
public class UserService {

  private LocalUserDAO localUserDAO;
  private EncryptionService encryptionService;
  private JWTService jwtService;

  public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
    this.localUserDAO = localUserDAO;
    this.encryptionService = encryptionService;
    this.jwtService = jwtService;
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
    user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
    return localUserDAO.save(user);
  }


  public String loginUser(LoginBody loginBody) {
    Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
    if (opUser.isPresent()) {
      LocalUser user = opUser.get();
      if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
        return jwtService.generateJWT(user);
      }
    }
    return null;
  }

}