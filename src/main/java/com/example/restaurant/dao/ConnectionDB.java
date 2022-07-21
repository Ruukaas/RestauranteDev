package com.example.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante";

    private static Connection currentConnection = null;

    public static Connection getCurrentConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        
        if(currentConnection == null){
            currentConnection = DriverManager.getConnection(URL, "root", "");
        }
        return currentConnection;
    }
    
    public static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(URL, "root", "");
       
    }

}
