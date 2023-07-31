package com.store.backend.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationBody {

  @NotNull
  @NotBlank
  @Size(min=3, max=255)
  private String username;

  @NotNull
  @NotBlank
  @Email
  private String email;

  @NotNull
  @NotBlank
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
  @Size(min=6, max=32)
  private String password;

  @NotNull
  @NotBlank
  private String firstName;

  @NotNull
  @NotBlank
  private String lastName;

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

public void setUsername(String username) {
	this.username = username;
}

public void setEmail(String email) {
	this.email = email;
}

public void setPassword(String password) {
	this.password = password;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}
}