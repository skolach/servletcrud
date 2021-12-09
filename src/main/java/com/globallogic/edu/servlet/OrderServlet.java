package com.globallogic.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globallogic.edu.dao.OrderDao;
import com.globallogic.edu.entity.Order;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(OrderServlet.class);

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orders = null;

        try {
            orders = OrderDao.getOrders();
        } catch (SQLException e) {
            log.error("Cannot get list of orders", e);
        }

        String ordersJson = gson.toJson(orders);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(ordersJson);
        out.flush();
        return;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doDelete(req, resp);
    }

}