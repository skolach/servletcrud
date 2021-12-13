package com.globallogic.edu.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbManager {

    private static final String CONNECTION_STRING =
        "jdbc:h2:~/taxvice;"
        + "INIT=runscript from 'classpath:scripts/create.sql'\\; "
        + "runscript from 'classpath:scripts/init.sql'"
        ;

    private static Connection connection;

    private DbManager() {}
    
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(CONNECTION_STRING);
        }
        return connection;
    }

    public static void main(String[] args){
        try (
                ResultSet rs = DbManager.getConnection().createStatement()
                        .executeQuery("select * from `order`");) {
            while (rs.next()) {
                System.out.println(rs.getDate("created_at") + " " + rs.getBigDecimal("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}