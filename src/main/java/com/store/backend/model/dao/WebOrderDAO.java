package com.store.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.store.backend.model.LocalUser;
import com.store.backend.model.WebOrder;

/**
 * Data Access Object to access WebOrder data.
 */
public interface WebOrderDAO extends ListCrudRepository<WebOrder, Long> {

  List<WebOrder> findByUser(LocalUser user);

}