package org.frank.webapp.servelet.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolDB {
    public static Connection getConnection() throws SQLException {
        Context initContext;
        Connection conn = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/ConnectionMySQL");
             conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        // etc

        return conn;
    }
}
