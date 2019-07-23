package com.codecool.shop.dao.JDBC_implementation;


import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(ProductCategory productCategory) {
        String query = "INSERT INTO product_categories (id, name, department, description) VALUES (?,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productCategory.getId());
            preparedStatement.setString(2, productCategory.getName());
            preparedStatement.setString(3, productCategory.getDepartment());
            preparedStatement.setString(4, productCategory.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
