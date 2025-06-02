package edu.lk.ijse.farm.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection{
    private static DBConnection dbConnection;
    private final Connection connection;


    private DBConnection() throws SQLException {
        String URL ="jdbc:mysql://localhost:3306/FarmSystem";
        String USER ="root";
        String PASSWORD ="sasmitha@1234";
        connection = DriverManager. getConnection(URL,USER,PASSWORD);
    }
    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

}
