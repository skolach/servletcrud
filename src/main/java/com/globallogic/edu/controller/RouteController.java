package com.globallogic.edu.controller;

import com.globallogic.edu.entity.Route;
import com.globallogic.edu.service.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/{id}")
    public String editRoute(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("route", routeService.getById(id));
        return "RouteView";
    }

    @PostMapping
    public String saveRoute(Route route){
        routeService.save(route);
        return "redirect:/order/" + route.getOrder().getId();
    }

    @GetMapping("delete/{id}")
    public String deleteRoute(@PathVariable("id") Integer id) {
        Integer orderId = routeService.getById(id).getOrder().getId();
        routeService.deleteById(id);
        return "redirect:/order/" + orderId;
    }
}
