package com.globallogic.edu.service;

import java.util.List;

import com.globallogic.edu.entity.Order;

public interface OrderService {

    public List<Order> getAll();

    public Order getById(Integer id);

    public Order save(Order order);

    public void delete(Integer id);

}