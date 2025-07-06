package edu.lk.ijse.farm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/FarmSystem";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "sasmitha@1234"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
