package com.codecool.shop.dao;

import com.codecool.shop.model.PaymentInfo;

import java.util.List;

public interface PaymentInfoDao {

    void add(PaymentInfo paymentInfo);
    PaymentInfo find(int id);
    void remove(int id);

    List<PaymentInfo> getAll();
}
