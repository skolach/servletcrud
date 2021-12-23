package com.globallogic.edu.service;

import java.util.List;

import com.globallogic.edu.entity.Order;
import com.globallogic.edu.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.getOne(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}