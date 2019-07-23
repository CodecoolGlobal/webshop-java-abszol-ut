package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;

import java.util.HashMap;

public class OrderDaoMem implements OrderDao {


    private HashMap<String, String> orderHash = new HashMap<>();
    private static OrderDaoMem instance = null;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void clearList() {
        orderHash.clear();
    }



    @Override
    public void add(String name, String data) {
        orderHash.put(name, data);
    }

    @Override
    public HashMap<String, String> getAll() {
        return orderHash;
    }
}
