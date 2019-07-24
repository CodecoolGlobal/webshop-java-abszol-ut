package com.codecool.shop.model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MakeDBConnection {

    public Connection getConnection() throws SQLException {
        try {

            Properties props = new Properties();
            String dbSettingsPropertyFile = "src/main/resources/connection.properties";
            FileReader fReader = new FileReader(dbSettingsPropertyFile);

            props.load(fReader);

            String dbDriverClass = props.getProperty("db.driver.class");
            String dbConnUrl = props.getProperty("db.conn.url");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");

            Class.forName(dbDriverClass);

            return DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not read db connection file", e);
        }
    }
}
