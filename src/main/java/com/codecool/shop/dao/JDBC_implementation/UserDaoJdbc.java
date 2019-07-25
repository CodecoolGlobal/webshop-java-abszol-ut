package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private static UserDaoJdbc instance = null;

    public UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }
    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(User user) {
        String query = "INSERT INTO users (ID, username, email, password) VALUES (DEFAULT,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    public Boolean checkValidUser(String inputUsername, String inputPassword){
        Boolean isUserValid = false;
        String query = "SELECT username, password FROM users " +
                       "WHERE username=(?)";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, inputUsername);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                if (result.getString("password").equals(inputPassword))
                    isUserValid = true;
            }
            return isUserValid;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
