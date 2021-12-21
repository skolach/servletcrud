package com.globallogic.edu.controller;

import java.sql.SQLException;
import java.util.List;

import com.globallogic.edu.entity.Order;
import com.globallogic.edu.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.log4j.Logger;

@Controller
public class OrderController {

    private static final Logger log = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private OrderRepository orderRepository;

    OrderController(OrderRepository repo) {
        this.orderRepository = repo;
    }

    @GetMapping(path = { "/", "/order" })
    public String doGet(
            Model model,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "new", required = false) String newCmd) {

        String forwardStr = null;
        if (newCmd != null) {
            model.addAttribute("order", new Order());
            forwardStr = "orderView";
        } else {
            if (id == null) {
                List<Order> orders = orderRepository.findAll();
                model.addAttribute("orders", orders);
                forwardStr = "ordersView";
            } else {
                Order order = null;
                try {
                    order = orderRepository.findById(Integer.parseInt(id))
                            .orElseThrow(() -> new SQLException("Order can't be find"));
                } catch (SQLException | NumberFormatException e) {
                    log.error("Can't get order by id = " + id, e);
                }
                model.addAttribute("order", order);
                forwardStr = "orderView";
            }
        }
        return forwardStr;
    }

    @PostMapping(path = { "/order" })
    public String doPost(
            Model model,
            @RequestParam(name = "delete", required = false) String delete,
            @ModelAttribute("order") Order editedOrder) {

        if (delete != null) {
            orderRepository.deleteById(editedOrder.getId());
        } else {
            if (editedOrder.getId() == null) {
                orderRepository.save(editedOrder);
                log.debug("Newly inserted order has id = " + editedOrder.getId());
            } else {
                Order oldOrder = orderRepository.findById(editedOrder.getId()).orElse(null);
                oldOrder.merge(editedOrder);
                log.debug("Order with id = " + oldOrder.getId() + " successfully updated in DB");
            }
            orderRepository.flush();
        }
        model.addAttribute("orders", orderRepository.findAll());
        return "ordersView";
    }
}