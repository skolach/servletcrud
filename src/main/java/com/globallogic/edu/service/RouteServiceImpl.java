package com.globallogic.edu.service;

import java.util.List;

import com.globallogic.edu.entity.Route;
import com.globallogic.edu.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> findByOrderId(Integer id) {
        return routeRepository.findByOrderId(id);
    }

    @Override
    public void deleteById(Integer id) {
        routeRepository.deleteById(id);
    }

    @Override
    public Route getById(Integer id) {
        return routeRepository.getById(id);
    }

    @Override
    public void save(Route route) {
        routeRepository.save(route);
    }

}