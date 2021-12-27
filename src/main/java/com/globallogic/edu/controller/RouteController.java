package com.globallogic.edu.controller;

import com.globallogic.edu.service.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("delete/{id}")
    public String deleteRoute(@PathVariable("id") Integer id) {
        Integer orderId = routeService.getById(id).getOrder().getId();
        routeService.deleteById(id);
        return "redirect:/order/" + orderId;
    }
}
