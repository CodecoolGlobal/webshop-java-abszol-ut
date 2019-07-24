package com.codecool.shop.dao.JDBC_implementation;


import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

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
        String query = "SELECT * FROM product_categories WHERE id=(?) ";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            ProductCategory resultCategory = null;
            if (result.next()){
                String tempName = result.getString("name");
                String tempDepartment = result.getString("department");
                String tempDescription = result.getString("description");
                resultCategory = new ProductCategory(id, tempName, tempDepartment, tempDescription);
            }
            return resultCategory;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
