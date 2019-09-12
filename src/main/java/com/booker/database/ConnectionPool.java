package com.booker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String driver = "org.postgresql.Driver";
    private static final String username = "vyzvffinhzurwi";
    private static final String password = "cc95d3e41b8bd02016b90adead770b9e3ce02362f118d19d8a21cd3e1d627fa5";
    private static final String dbUrl = "jdbc:postgresql://ec2-107-20-168-237.compute-1.amazonaws.com:5432/dfmoitgrtekd1u";

//    private static final String username = "postgres";
//    private static final String password = "admin";
//    private static final String dbUrl = "jdbc:postgresql://localhost:5433/postgres";

    private ConnectionPool(){
    }

    private Connection connection = getConnection();

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    Connection getConnection() {

        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
