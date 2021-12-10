package com.globallogic.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void insertOrder(Order order) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);// NOSONAR
            int k = 1;
            pstmt.setInt(k++, order.getUserId());
            pstmt.setTimestamp(k++, order.getCreatedAt());
            pstmt.setTimestamp(k++, order.getStartAt());
            pstmt.setTimestamp(k++, order.getEndAt());
            pstmt.setBigDecimal(k++, order.getPrice());
            pstmt.setInt(k++, order.getRouteDiscount());
            pstmt.setInt(k++, order.getUserDiscount());
            pstmt.setBigDecimal(k, order.getCash());

            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
                rs.close();
            }
            pstmt.close();
        } catch (SQLException ex) {
            DbManager.getInstance().rollbackAndClose(con);
            log.error("Cannot insert order to DB", ex);
            throw new SQLException(ex);
        } finally {
            DbManager.getInstance().commitAndClose(con);
        }
    }

    public static Order getOrder(String id) throws SQLException {
        Order result = new Order();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DbManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ORDER); // NOSONAR
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();

            if (rs.last() && (rs.getRow() == 1)) {
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
            }
            rs.close();
            pstmt.close();
        } catch (SQLException | NumberFormatException ex) {
            DbManager.getInstance().rollbackAndClose(con);
            log.error("Cannot get order by id = " + id, ex);
            throw new SQLException(ex);
        } finally {
            DbManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    public static List<Order> getOrders() throws SQLException {
        List<Order> result = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DbManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ORDERS);
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
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            DbManager.getInstance().rollbackAndClose(con);
            log.error("Cannot get list of orders", ex);
            throw new SQLException(ex);
        } finally {
            DbManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    public static void main(String[] args) throws SQLException {
        for (Order o : getOrders()) {
            System.out.println(o);
        }
    }

}