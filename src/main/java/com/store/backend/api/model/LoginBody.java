package com.store.backend.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginBody {

  private String username;

  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}