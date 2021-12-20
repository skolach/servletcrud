package com.globallogic.edu.controller;

import java.sql.SQLException;
import java.util.List;

import com.globallogic.edu.dao.OrderDao;
import com.globallogic.edu.entity.Order;

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

    @GetMapping(path = {"/", "/order"})
    public String doGet(
            Model model,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "new", required = false) String newCmd)
    {

        String forwardStr = null;
        if (newCmd != null) {
            model.addAttribute("order", new Order());
            forwardStr = "orderView";
        } 
        else {
            if (id == null) {
                List<Order> orders = null;
                try {
                    orders = OrderDao.getOrders();
                } catch (SQLException e) {
                    log.error("Cannot get list of orders", e);
                }
                model.addAttribute("orders", orders);
                forwardStr = "ordersView";
            } else {
                Order order = null;
                try {
                    order = OrderDao.getOrder(Integer.parseInt(id));
                } catch (SQLException | NumberFormatException e) {
                    log.error("Can't get order by id = " + id, e);
                }
                model.addAttribute("order", order);
                forwardStr = "orderView";
            }
        }
        return forwardStr;
    }

    @PostMapping(path = {"/order"})
    public String doPost(
            Model model,
            @RequestParam(name = "delete", required = false) String delete,
            @ModelAttribute("order") Order editedOrder)
    {

        if (delete != null) {
            try {
                OrderDao.deleteOrder(editedOrder.getId());
            } catch (SQLException e) {
                log.error("Can't delete order with id=" + delete, e);
            }
        } else {
            try {
                Order oldOrder = OrderDao.getOrder(editedOrder.getId());
                if (oldOrder == null) {
                    OrderDao.insertOrder(editedOrder);
                    log.debug("Newly inserted order has id = " + editedOrder.getId());
                } else {
                    oldOrder.merge(editedOrder);
                    OrderDao.updateOrder(oldOrder);
                    log.debug("Order with id = " + editedOrder.getId() + " successfully updated in DB");
                }
            } catch (SQLException e) {
                log.error("Can't save order", e);
            }
        }

        try {
            model.addAttribute("orders", OrderDao.getOrders());
        } catch (SQLException e) {
            log.error("Can't get orders list", e);
        }
        return "ordersView";
    }
}