package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (id, name, description, price, currency, supplier_id, category_id) VALUES (?,?,?,?,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setFloat(4, product.getFloatPrice());
            preparedStatement.setString(5, product.getDefCurrencyString());
            preparedStatement.setInt(6, product.getSupplier().getId());
            preparedStatement.setInt(7, product.getProductCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
