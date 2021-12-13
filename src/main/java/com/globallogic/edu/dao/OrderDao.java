package com.globallogic.edu.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.globallogic.edu.dbmanager.DbManager;
import com.globallogic.edu.entity.Order;

public class OrderDao {

    private OrderDao() {
    }

    private static final Logger log = Logger.getLogger(OrderDao.class.getName());

    private static final String SQL_INSERT_ORDER = "INSERT INTO `order`" +
            "(`user_id`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ORDERS = "select o.`id`, o.`user_id`, o.`created_at`, o.`start_at`, o.`end_at`, "
            +
            "o.`price`, o.`route_discount`, o.`user_discount`, o.`cash` " +
            "from `order` o";

    private static final String SQL_SELECT_ORDER = "select o.`id`, o.`user_id`, o.`created_at`, o.`start_at`, o.`end_at`, "
            +
            "o.`price`, o.`route_discount`, o.`user_discount`, o.`cash` " +
            "from `order` o " +
            "where o.id = ? ";

    public static Order getOrder(String id) throws SQLException {
        Order order = new Order();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DbManager.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ORDER); // NOSONAR
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();

            if (rs.last() && (rs.getRow() == 1)) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setStartAt(rs.getTimestamp("start_at"));
                order.setEndAt(rs.getTimestamp("end_at"));
                order.setPrice(rs.getBigDecimal("price"));
                order.setRouteDiscount(rs.getInt("route_discount"));
                order.setUserDiscount(rs.getInt("user_discount"));
                order.setCash(rs.getBigDecimal("cash"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException | NumberFormatException ex) {
            log.error("Cannot get order by id = " + id, ex);
            throw new SQLException(ex);
        }
        return order;
    }

    public static List<Order> getOrders() throws SQLException {
        Statement stmt = null;
        List<Order> result = new ArrayList<>();
        try {
            Connection con = DbManager.getConnection();
            stmt = con.createStatement(); //NOSONAR
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ORDERS);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setStartAt(rs.getTimestamp("start_at"));
                order.setEndAt(rs.getTimestamp("end_at"));
                order.setPrice(rs.getBigDecimal("price"));
                order.setRouteDiscount(rs.getInt("route_discount"));
                order.setUserDiscount(rs.getInt("user_discount"));
                order.setCash(rs.getBigDecimal("cash"));

                result.add(order);
            }
            stmt.close();
        } catch (SQLException ex) {
            log.error("Cannot get list of orders", ex);
            throw new SQLException(ex);
        }
        return result;
    }

    public static void insertOrder(Order order) throws SQLException {
        try {
            Connection con = DbManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS); //NOSONAR
                //(`user_id`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`)
            int k = 0;
            pstmt.setInt(++k, 1);
            pstmt.setTimestamp(++k, order.getStartAt());
            pstmt.setTimestamp(++k, order.getEndAt());
            pstmt.setBigDecimal(++k, order.getPrice());
            pstmt.setInt(++k, order.getRouteDiscount());
            pstmt.setInt(++k, order.getUserDiscount());
            pstmt.setBigDecimal(++k, order.getCash());
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
                rs.close();
            }
        } catch (SQLException e) {
            log.error("Can't insert new order to the DB", e);
        }
    }

    public static void main(String[] args) throws SQLException {

        for (Order o : getOrders()) {
            System.out.println(o.getStartAt() + " " + o.getUserId());
        }

        Order order = new Order();
        //(`user_id`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`)
        order.setUserId(999);
        order.setStartAt(Timestamp.valueOf("1010-10-10 10:10:10"));
        order.setEndAt(Timestamp.valueOf("1010-10-10 10:10:10"));        
        order.setPrice(new BigDecimal("10.10"));
        order.setRouteDiscount(10);
        order.setUserDiscount(10);
        order.setCash(new BigDecimal("10.10"));
        insertOrder(order);

        System.out.println("----------");

        for (Order o : getOrders()) {
            System.out.println(o.getStartAt() + " " + o.getUserId());
        }
    }
}