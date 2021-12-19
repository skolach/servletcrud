package com.globallogic.edu.controller;

import java.sql.SQLException;
import java.util.List;

import com.globallogic.edu.dao.OrderDao;
import com.globallogic.edu.entity.Order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // @Override
    // protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     String delete = req.getParameter("delete");
    //     if (delete != null) {
    //         try {
    //             OrderDao.deleteOrder(Integer.parseInt(delete));
    //         } catch (NumberFormatException | SQLException e) {
    //             log.error("Can't delete order with id=" + delete, e);
    //         }
    //     } else {
    //         try {
    //             Order newOrder = new Order();

    //             try {
    //                 newOrder.setId(Integer.parseInt(req.getParameter("id")));
    //             } catch (NumberFormatException e) {
    //                 newOrder.setId(null);
    //             }
    //             newOrder.setUserId(Integer.parseInt(req.getParameter("userId")));
    //             newOrder.setCreatedAt(Timestamp.valueOf(req.getParameter("createdAt")));
    //             newOrder.setStartAt(Timestamp.valueOf(req.getParameter("startAt")));
    //             newOrder.setEndAt(Timestamp.valueOf(req.getParameter("endAt")));
    //             newOrder.setPrice(new BigDecimal(req.getParameter("price")));
    //             newOrder.setRouteDiscount(Integer.parseInt(req.getParameter("routeDiscount")));
    //             newOrder.setUserDiscount(Integer.parseInt(req.getParameter("userDiscount")));
    //             newOrder.setCash(new BigDecimal(req.getParameter("cash")));

    //             Order oldOrder = OrderDao.getOrder(newOrder.getId());

    //             if (oldOrder == null) {
    //                 OrderDao.insertOrder(newOrder);
    //                 log.debug("Newly inserted order has id = " + newOrder.getId());
    //             } else {
    //                 oldOrder.merge(newOrder);
    //                 OrderDao.updateOrder(oldOrder);
    //                 log.debug("Order with id = " + newOrder.getId() + " successfully updated in DB");
    //             }
    //         } catch (SQLException | NumberFormatException e) {
    //             log.error("Can't save order", e);
    //         }
    //     }
    //     resp.sendRedirect("order");
    // }

}