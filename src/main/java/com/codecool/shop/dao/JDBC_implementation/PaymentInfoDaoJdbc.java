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

    private static PaymentInfoDaoJdbc instance = null;

    private PaymentInfoDaoJdbc() {
    }

    public static PaymentInfoDaoJdbc getInstance() {
        if (instance == null) {
            instance = new PaymentInfoDaoJdbc();
        }
        return instance;
    }

    private MakeDBConnection makeDBConnection = new MakeDBConnection();

    @Override
    public void add(PaymentInfo paymentInfo) {
        String query = "INSERT INTO payment_info (user_id, name, card_number, month, year) VALUES (?,?,?,?,?);";
        try (Connection connection = makeDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paymentInfo.getUserID());
            preparedStatement.setString(2, paymentInfo.getName());
            preparedStatement.setInt(3, paymentInfo.getCardNumber());
            preparedStatement.setInt(4, paymentInfo.getMonth());
            preparedStatement.setInt(5, paymentInfo.getYear());
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
