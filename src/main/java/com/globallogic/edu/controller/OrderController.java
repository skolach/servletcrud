package com.globallogic.edu.controller;

import java.sql.SQLException;
import java.util.List;

import com.globallogic.edu.entity.Order;
import com.globallogic.edu.repository.OrderRepository;
import com.globallogic.edu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private OrderService orderService;

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
        return "orderView";
    }

    @PostMapping
    public String updateOrder(Order order) {
        orderService.save(order);
        return "redirect:/order";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderService.delete(id);
        return "redirect:/order";
    }

//////////////////////////////////////////////////////

    // @GetMapping
    // public String doGet(
    //         Model model,
    //         @RequestParam(name = "id", required = false) String id,
    //         @RequestParam(name = "new", required = false) String newCmd) {

    //     String forwardStr = null;
    //     if (newCmd != null) {
    //         model.addAttribute("order", new Order());
    //         forwardStr = "orderView";
    //     } else {
    //         if (id == null) {
    //             List<Order> orders = orderService.findAll();
    //             model.addAttribute("orders", orders);
    //             forwardStr = "ordersView";
    //         } else {
    //             Order order = null;
    //             try {
    //                 order = orderService.findById(Integer.parseInt(id))
    //                         .orElseThrow(SQLException::new);
    //                 model.addAttribute("order", order);
    //                 forwardStr = "orderView";
    //             } catch (SQLException | NumberFormatException e) {
    //                 log.error("Can't get order by id = " + id, e);
    //             }
    //         }
    //     }
    //     return forwardStr;
    // }

    // @PostMapping(path = { "/order" })
    // public String doPost(
    //         Model model,
    //         @RequestParam(name = "delete", required = false) String delete,
    //         @ModelAttribute("order") Order editedOrder) {

    //     if (delete != null) {
    //         orderService.deleteById(editedOrder.getId());
    //     } else {
    //             orderService.save(editedOrder);
    //     }
    //     model.addAttribute("orders", orderService.findAll());
    //     return "ordersView";
    // }
}