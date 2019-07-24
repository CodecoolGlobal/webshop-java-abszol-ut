package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.OrderedProduct;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
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
