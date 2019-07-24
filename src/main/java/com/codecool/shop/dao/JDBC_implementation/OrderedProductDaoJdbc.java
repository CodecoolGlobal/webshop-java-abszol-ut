package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.OrderedProductDao;
import com.codecool.shop.model.AddressInfo;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.OrderedProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderedProductDaoJdbc implements OrderedProductDao {

    private static OrderedProductDaoJdbc instance = null;

    private OrderedProductDaoJdbc() {
    }

    public static OrderedProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderedProductDaoJdbc();
        }
        return instance;
    }

    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(OrderedProduct orderedProduct) {
        String query = "INSERT INTO orders (user_id, product_id, qty, payed) VALUES (?,?,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderedProduct.getUserID());
            preparedStatement.setInt(2, orderedProduct.getProductID());
            preparedStatement.setInt(3, orderedProduct.getQty());
            preparedStatement.setBoolean(4, orderedProduct.isPayed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public OrderedProduct find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<OrderedProduct> getAll() {
        return null;
    }
}
