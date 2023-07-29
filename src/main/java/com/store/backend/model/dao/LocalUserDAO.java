package com.store.backend.model.dao;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.store.backend.model.LocalUser;

public interface LocalUserDAO extends ListCrudRepository<LocalUser, Long>{

	  Optional<LocalUser> findByUsernameIgnoreCase(String username);

	  Optional<LocalUser> findByEmailIgnoreCase(String email);
}
