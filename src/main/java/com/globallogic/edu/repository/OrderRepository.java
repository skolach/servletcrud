package com.globallogic.edu.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import com.globallogic.edu.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query(
        value = "INSERT INTO `order` "
            + "(`user_id`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?) ",
        nativeQuery = true
    )
    void insertOrder(Integer userId, Timestamp startAt, Timestamp endAt,
        BigDecimal price, Integer routeDiscount, Integer userDiscount, BigDecimal cash);
}