package com.globallogic.edu.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        Object result = null;
        String id = req.getParameter("id");
        if (id == null) {
            try {
                result = OrderDao.getOrders();
            } catch (SQLException e) {
                log.error("Cannot get list of orders", e);
            }
        } else {
            try{
                result = OrderDao.getOrder(Integer.parseInt(id));
            } catch (SQLException | NumberFormatException e) {
                log.error("Can't get order by id = " + id, e);
            }
        }
        String ordersJson = gson.toJson(result);
        try (PrintWriter out = resp.getWriter()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(ordersJson);
            out.flush();
        } catch (IOException e){
            log.error("Can't write response", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            order = gson.fromJson(sb.toString(), Order.class);
            OrderDao.insertOrder(order);
            log.debug("Newly inserted order has id = " + order.getId());
        } catch (IOException e) {
            log.error("Can't open reader for request body", e);
        } catch (JsonSyntaxException e) {
            log.error("Can't convert from json to object. Syntax error", e);
        } catch (SQLException e) {
            log.error("Can't write new order to DB", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order anotherOrder = null;
        Order order = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            anotherOrder = gson.fromJson(sb.toString(), Order.class);
            order = OrderDao.getOrder(anotherOrder.getId());
            order.merge(anotherOrder);
            OrderDao.updateOrder(order);
        } catch (IOException e) {
            log.error("Can't open reader for request body", e);
        } catch (JsonSyntaxException e) {
            log.error("Can't convert from json to object. Syntax error", e);
        } catch (SQLException e) {
            log.error("Can't update order in DB", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            OrderDao.deleteOrder(id);
        } catch (NumberFormatException e) {
            log.error("Wrong number format for order id, can't delete.", e);
        } catch (SQLException e) {
            log.error("Can't delete order from db", e );
        }
    }
}