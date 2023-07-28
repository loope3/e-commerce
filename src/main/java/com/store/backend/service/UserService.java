package com.store.backend.service;

import org.springframework.stereotype.Service;

import com.store.backend.api.model.RegistrationBody;
import com.store.backend.exception.UserAlreadyExistsException;
import com.store.backend.model.LocalUser;
import com.store.backend.model.dao.LocalUserDAO;

@Service
public class UserService {

  private LocalUserDAO localUserDAO;

  public UserService(LocalUserDAO localUserDAO) {
    this.localUserDAO = localUserDAO;
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
    //TODO: Encrypt passwords!!
    user.setPassword(registrationBody.getPassword());
    return localUserDAO.save(user);
  }

}