package com.codecool.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeDBConnection {

    private static final String DATABASE = System.getProperty("db.url");
    private static final String DB_USER = System.getProperty("db.user");
    private static final String DB_PASSWORD = System.getProperty("db.pass");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }


}
