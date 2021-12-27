package com.globallogic.edu.controller;

import com.globallogic.edu.entity.Order;
import com.globallogic.edu.service.OrderService;
import com.globallogic.edu.service.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RouteService routeService;

    @GetMapping
    public String getOrders(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "ordersView";
    }

    @GetMapping("newOrder")
    public String newOrder(Model model){
        model.addAttribute("order", new Order());
        return "orderView";
    }

    @GetMapping("/{id}")
    public String editOrder(@PathVariable("id") Integer id, Model model){
        model.addAttribute("order", orderService.getById(id));
        model.addAttribute("routes", routeService.findByOrderId(id));
        return "orderView";
    }

    @PostMapping
    public String updateOrder(Order order) {
        orderService.save(order);
        return "redirect:/order";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderService.delete(id);
        return "redirect:/order";
    }
}