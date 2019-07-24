package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.AddressInfoDao;
import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.model.AddressInfo;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddressInfoDaoJdbc implements AddressInfoDao {

    private static AddressInfoDaoJdbc instance = null;

    private AddressInfoDaoJdbc() {
    }

    public static AddressInfoDaoJdbc getInstance() {
        if (instance == null) {
            instance = new AddressInfoDaoJdbc();
        }
        return instance;
    }

    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(AddressInfo addressInfo) {
        String query = "INSERT INTO address_info (user_id, full_name, address, city, state, zip) VALUES (?,?,?,?,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, addressInfo.getUserID());
            preparedStatement.setString(2, addressInfo.getFullName());
            preparedStatement.setString(3, addressInfo.getAddress());
            preparedStatement.setString(4, addressInfo.getCity());
            preparedStatement.setString(5, addressInfo.getState());
            preparedStatement.setInt(6, addressInfo.getZip());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public AddressInfo find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<AddressInfo> getAll() {
        return null;
    }
}
