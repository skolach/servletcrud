package com.globallogic.edu.dbmanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

public class DbManager {

    private static final Logger log = Logger.getLogger(DbManager.class);

    private static DbManager instance;

    private DbManager() {}

    public static synchronized DbManager getInstance() {
        if (instance == null)
            instance = new DbManager();
        return instance;
    }

    public Connection getConnection() throws SQLException, NamingException {
        
        Context initContext = new InitialContext();

        Context envContext = (Context) initContext.lookup("java:/comp/dev");

        DataSource ds = (DataSource) envContext.lookup("jdbc/taxvice");

        return ds.getConnection();
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