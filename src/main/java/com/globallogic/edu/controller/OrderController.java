package com.globallogic.edu.controller;

import com.globallogic.edu.entity.Order;
import com.globallogic.edu.entity.OrderDto;
import com.globallogic.edu.entity.OrderDtoMapper;
import com.globallogic.edu.entity.Route;
import com.globallogic.edu.entity.RouteDto;
import com.globallogic.edu.entity.RouteDtoMapper;
import com.globallogic.edu.service.OrderService;
import com.globallogic.edu.service.RouteService;

import org.mapstruct.factory.Mappers;
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

    private OrderDtoMapper orderDtoMapper = Mappers.getMapper(OrderDtoMapper.class);
    private RouteDtoMapper routeDtoMapper = Mappers.getMapper(RouteDtoMapper.class);

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
        model.addAttribute("order", orderDtoMapper.orderToOrderDto(orderService.getById(id)));
        model.addAttribute("routes", routeService.findByOrderId(id));
        return "orderView";
    }

    @PostMapping(params = "action=save")
    public String updateOrder(OrderDto orderDto) {
        orderService.save(orderDtoMapper.orderDtoToOrder(orderDto));
        return "redirect:/order";
    }

    @PostMapping(params = "action=newPoint")
    public String newPoint(OrderDto orderDto, Model model) {
        RouteDto newRouteDto = routeDtoMapper.routeToRouteDto(new Route());
        newRouteDto.setOrder(orderDtoMapper.orderDtoToOrder(orderDto));
        model.addAttribute("route", newRouteDto);
        return "RouteView";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderService.delete(id);
        return "redirect:/order";
    }
}