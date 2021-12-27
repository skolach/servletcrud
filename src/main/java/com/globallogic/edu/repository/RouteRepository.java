package com.globallogic.edu.repository;

import java.util.List;

import com.globallogic.edu.entity.Route;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer>{

    List<Route> findByOrderId(Integer id);

    Route getById(Integer id);

}
