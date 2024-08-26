package org.frank.webapp.servelet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    
    private static String url = "jdbc:mysql://localhost:3306/curso_java?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }
}
