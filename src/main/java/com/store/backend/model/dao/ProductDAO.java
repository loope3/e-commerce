package com.store.backend.model.dao;

import org.springframework.data.repository.ListCrudRepository;

import com.store.backend.model.Product;

/**
 * Data Access Object for accessing Product data.
 */
public interface ProductDAO extends ListCrudRepository<Product, Long> {
}