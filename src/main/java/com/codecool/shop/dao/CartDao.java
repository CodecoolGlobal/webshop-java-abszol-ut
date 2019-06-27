package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface CartDao {

    void add(Product product);

    void remove(Product product);

    List<Product> getAll();

}
