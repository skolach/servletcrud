package com.globallogic.edu.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DbManager {

    private static final String CONNECTION_STRING =
        "jdbc:h2:~/taxvice;INIT=runscript from 'classpath:scripts/create.sql'\\; runscript from 'classpath:scripts/init.sql'";

    private static final Logger log = Logger.getLogger(DbManager.class);

    private static DbManager instance;

    private DbManager() {}

    public static DbManager getInstance() {
        if (instance == null)
            instance = new DbManager();
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING);
    }

    public void rollbackAndClose(Connection con) throws SQLException{
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            log.error("Cannot rollback transaction ", ex);
            throw new SQLException(ex);
        }
    }

    public void commitAndClose(Connection con) throws SQLException{
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            log.error("Cannot commit transaction ", ex);
            throw new SQLException(ex);
        }
    }

    public static void main(String[] args){
        try (
            ResultSet rs = 
                DbManager.getInstance().getConnection().createStatement().
                executeQuery("select * from `order`");
        ) {
            while(rs.next()) {
                System.out.println(rs.getDate("created_at") + " " + rs.getBigDecimal("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}