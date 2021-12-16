package com.globallogic.edu.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globallogic.edu.dao.OrderDao;
import com.globallogic.edu.entity.Order;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(OrderServlet.class);

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String forwardStr = null;
        if (id == null) {
            List<Order> orders = null;
            try {
                orders = OrderDao.getOrders();
            } catch (SQLException e) {
                log.error("Cannot get list of orders", e);
            }
            req.setAttribute("orders", orders);
            forwardStr = "/WEB-INF/views/ordersView.jsp";
        } else {
            Order order = null;
            try{
                order = OrderDao.getOrder(Integer.parseInt(id));
            } catch (SQLException | NumberFormatException e) {
                log.error("Can't get order by id = " + id, e);
            }
            req.setAttribute("order", order);
            forwardStr = "/WEB-INF/views/orderView.jsp";
        }
        RequestDispatcher dispatcher = 
            this.getServletContext().getRequestDispatcher(forwardStr);
        try {
            dispatcher.forward(req, resp);
        } catch ( ServletException | IOException e) {
            log.error("Can't get order(s)", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order newOrder = new Order();
        String delete = req.getParameter("delete");
        try {
            if (delete != null) {
                OrderDao.deleteOrder(Integer.parseInt(delete));
            }
            newOrder.setId(Integer.parseInt(req.getParameter("id")));
            newOrder.setUserId(Integer.parseInt(req.getParameter("userId")));
            newOrder.setCreatedAt(Timestamp.valueOf(req.getParameter("createdAt")));
            newOrder.setStartAt(Timestamp.valueOf(req.getParameter("startAt")));
            newOrder.setEndAt(Timestamp.valueOf(req.getParameter("endAt")));
            newOrder.setPrice(new BigDecimal(req.getParameter("price")));
            newOrder.setRouteDiscount(Integer.parseInt(req.getParameter("routeDiscount")));
            newOrder.setUserDiscount(Integer.parseInt(req.getParameter("userDiscount")));
            newOrder.setCash(new BigDecimal(req.getParameter("cash")));

            Order oldOrder = OrderDao.getOrder(newOrder.getId());

            if (oldOrder == null) {
                OrderDao.insertOrder(newOrder);
                log.debug("Newly inserted order has id = " + newOrder.getId());
            }else{
                oldOrder.merge(newOrder);
                OrderDao.updateOrder(oldOrder);
                log.debug("Order with id = " + newOrder.getId() + " successfully updated in DB");
            }
            resp.sendRedirect("order");
        } catch (SQLException | NumberFormatException | IOException e) {
            log.error("Can't save order", e);
        }
    }
}