package com.codecool.shop.dao.implementation.registration;

import java.sql.*;
import java.util.List;

public class RegisterDaoWithJDBC implements RegisterDao {



    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Override
    public void add(User user) {
        String query = "INSERT INTO users (id,username, email, password) " +
                "VALUES ('" + users.id + "', '" + users.username + "', '" + users.email + "', '"+ users.password + "');";
        executeQuery(query);
    }

//    @Override
//    public List<Register> all() {
//        return null;
//    }


//    @Override
//    public List<Todo> all() {
//        String query = "SELECT * FROM todos;";
//
//        List<Todo> resultList = new ArrayList<>();
//
//        try (Connection connection = getConnection();
//             Statement statement =connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query);
//        ){
//            while (resultSet.next()){
//                Todo actTodo = new Todo(resultSet.getString("title"),
//                        resultSet.getString("id"),
//                        Status.valueOf(resultSet.getString("status")));
//                resultList.add(actTodo);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return resultList;
//    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

