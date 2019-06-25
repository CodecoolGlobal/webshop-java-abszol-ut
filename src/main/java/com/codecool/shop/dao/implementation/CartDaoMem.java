package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> data = new ArrayList<>();
    private HashMap<Product, Integer> productsAndQty = new HashMap<>();
    private static CartDaoMem instance = null;


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
        }
        return productsAndQty;

    }


    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
