package com.codecool.shop.dao;



import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.HashMap;
import java.util.List;

public interface OrderDao {

    void add(String name, String data);

    HashMap<String, String> getAll();

}