package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

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
        /**
        String query = "SELECT * FROM products WHERE category_id=(?) ";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (result.next()){
                int tempId = result.getInt("id");
                String tempName = result.getString("name");
                String tempDescription = result.getString("description");
                Float tempPrice = result.getFloat("price");
                String tempCurrency = result.getString("currency");
                int tempCategoryId = result.getInt("category_id");
                int tempSupplierId = result.getInt("supplier_id");
                products.add(new Product(tempName, tempPrice, tempCurrency, tempDescription, tempCategoryId, tempSupplierId, ));
            }
            return
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
         **/
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
        String query = "SELECT * FROM products WHERE supplier_id=(?) ";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, supplier.getId());
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (result.next()){
                int tempId = result.getInt("id");
                String tempName = result.getString("name");
                String tempDescription = result.getString("description");
                Float tempPrice = result.getFloat("price");
                String tempCurrency = result.getString("currency");
                int tempCategoryId = result.getInt("category_id");
                ProductCategory tempProductCategory = ProductCategoryDaoJdbc.getInstance().find(tempCategoryId);
                int tempSupplierId = result.getInt("supplier_id");
                Supplier tempSupplier = SupplierDaoJdbc.getInstance().find(tempSupplierId);
                products.add(new Product(tempId, tempName, tempPrice, tempCurrency, tempDescription, tempProductCategory, tempSupplier));
            }
            return products;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE category_id=(?) ";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productCategory.getId());
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (result.next()){
                int tempId = result.getInt("id");
                String tempName = result.getString("name");
                String tempDescription = result.getString("description");
                Float tempPrice = result.getFloat("price");
                String tempCurrency = result.getString("currency");
                int tempCategoryId = result.getInt("category_id");
                ProductCategory tempProductCategory = ProductCategoryDaoJdbc.getInstance().find(tempCategoryId);
                int tempSupplierId = result.getInt("supplier_id");
                Supplier tempSupplier = SupplierDaoJdbc.getInstance().find(tempSupplierId);
                products.add(new Product(tempId, tempName, tempPrice, tempCurrency, tempDescription, tempProductCategory, tempSupplier));
            }
            return products;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
