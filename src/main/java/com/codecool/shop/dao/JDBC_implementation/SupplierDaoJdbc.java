package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers (id, name, description) VALUES (?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3, supplier.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
