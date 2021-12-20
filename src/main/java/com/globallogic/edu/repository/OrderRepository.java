package com.globallogic.edu.repository;

import com.globallogic.edu.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}