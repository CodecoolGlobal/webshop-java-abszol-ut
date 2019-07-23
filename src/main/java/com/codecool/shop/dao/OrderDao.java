package com.codecool.shop.dao;

import java.util.HashMap;

public interface OrderDao {

    void add(String name, String data);

    HashMap<String, String> getAll();

}