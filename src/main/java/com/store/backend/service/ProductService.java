package com.store.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.backend.model.Product;
import com.store.backend.model.dao.ProductDAO;

@Service
public class ProductService {

  private ProductDAO productDAO;

  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  public List<Product> getProducts() {
    return productDAO.findAll();
  }

}