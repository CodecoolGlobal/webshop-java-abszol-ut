package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private List<Product> data = new ArrayList<>();
    private HashMap<Product, Integer> productsAndQty = new HashMap<>();
    private static CartDaoMem instance = null;
    private int itemCounter = 0;


    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    public void clearList() {
        data.clear();
    }

    public HashMap<Product, Integer> getProductsAndQty() {
        for (Product product : data) {
            if (productsAndQty.containsKey(product)) {
                productsAndQty.put(product, productsAndQty.get(product) + 1);
            } else {
                productsAndQty.put(product, 1);
            }
            itemCounter++;
        }
        return productsAndQty;

    }


    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public void remove(int id) {
        for (Map.Entry<Product, Integer> e : productsAndQty.entrySet()) {
            if (e.getKey().getId() == id && e.getValue() > 1) {
                e.setValue(e.getValue() - 1);
                itemCounter--;
            } else {
                productsAndQty.remove(e.getKey());
                itemCounter--;
            }
        }
    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
