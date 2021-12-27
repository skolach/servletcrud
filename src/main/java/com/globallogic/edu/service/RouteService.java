package com.globallogic.edu.service;

import java.util.List;

import com.globallogic.edu.entity.Route;

public interface RouteService {

    public List<Route> findByOrderId( Integer id);

    public void deleteById(Integer id);

    public Route getById(Integer id);

}
