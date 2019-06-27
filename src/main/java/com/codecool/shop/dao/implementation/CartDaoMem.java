package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;


import java.util.*;

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
        return productsAndQty;
    }

    public HashMap<Product, Integer> setAndGetProductsAndQty() {
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

    public void resetCart() {
        this.productsAndQty = new HashMap<>();
    }

    public float getTotalPrice() {
        float result = 0;
        for (Map.Entry<Product, Integer> e : productsAndQty.entrySet()) {
            result += e.getKey().getDefaultPrice() * e.getValue();
        }
        return result;
    }


    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public void remove(Product product) {
        for (Iterator<Map.Entry<Product, Integer>> iterator = productsAndQty.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Product, Integer> entry = iterator.next();
            if (entry.getKey() == product && entry.getValue() > 1) {
                entry.setValue(entry.getValue() - 1);
                itemCounter--;
            } else if (entry.getKey() == product) {
                iterator.remove();
                itemCounter--;
            }
        }
    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
