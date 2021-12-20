package com.globallogic.edu.repository;

import java.util.List;
import java.util.Optional;

import com.globallogic.edu.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Override
    @Query(
        value =
            "SELECT o.`id`, o.`user_id`, o.`created_at`, o.`start_at`, o.`end_at`, "
            + "o.`price`, o.`route_discount`, o.`user_discount`, o.`cash` "
            + "FROM `order` o ",
        nativeQuery = true)
    List<Order> findAll();

    @Override
    @Query(
        value =
            "SELECT o.`id`, o.`user_id`, o.`created_at`, o.`start_at`, o.`end_at`, "
            + "o.`price`, o.`route_discount`, o.`user_discount`, o.`cash` "
            + "FROM `order` o "
            + "WHERE o.id = ? ",
        nativeQuery = true)
    Optional<Order> findById(Integer id);

    @Override
    @Modifying
    @Query(
        value =
            "DELETE FROM `order` o WHERE o.`id` = ?",
        nativeQuery = true
    )
    void deleteById(Integer id);

    @Override
    @Modifying
    @Query(
        value = "UPDATE `order` "
            + "SET `user_id` = ?, `start_at` = ?, `end_at` = ?, `price` = ?, `route_discount` = ?, "
            + "`user_discount` = ?, `cash` = ? "
            + "WHERE `id` = ? ",
        nativeQuery = true
    )
    Order saveAndFlush(Order order);

    @Modifying
    @Query(
        value = "INSERT INTO `order` "
            + "(`user_id`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?) ",
        nativeQuery = true
    )
    Order insertOrder(Order order);

}