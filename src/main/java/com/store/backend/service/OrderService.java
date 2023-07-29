package com.store.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.backend.model.LocalUser;
import com.store.backend.model.WebOrder;
import com.store.backend.model.dao.WebOrderDAO;

@Service
public class OrderService {

  private WebOrderDAO webOrderDAO;

  public OrderService(WebOrderDAO webOrderDAO) {
    this.webOrderDAO = webOrderDAO;
  }

  public List<WebOrder> getOrders(LocalUser user) {
    return webOrderDAO.findByUser(user);
  }

}