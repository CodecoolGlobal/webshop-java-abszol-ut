package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.DataAccessException;
import com.codecool.shop.dao.PaymentInfoDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.OrderedProduct;
import com.codecool.shop.model.PaymentInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentInfoDaoJdbc implements PaymentInfoDao {
    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(PaymentInfo paymentInfo) {
        String query = "INSERT INTO payment_info (id, user_id, name, card_number, month, year) VALUES (?,?,?,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paymentInfo.getID());
            preparedStatement.setInt(2, paymentInfo.getUserID());
            preparedStatement.setString(3, paymentInfo.getName());
            preparedStatement.setInt(4, paymentInfo.getCardNumber());
            preparedStatement.setInt(5, paymentInfo.getMonth());
            preparedStatement.setInt(6, paymentInfo.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    @Override
    public PaymentInfo find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<PaymentInfo> getAll() {
        return null;
    }
}
