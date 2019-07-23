package com.codecool.shop.dao;

import com.codecool.shop.model.OrderedProduct;

import java.util.List;

public interface OrderedProductDao {
    void add(OrderedProduct orderedProduct);
    OrderedProduct find(int id);
    void remove(int id);

    List<OrderedProduct> getAll();
}
