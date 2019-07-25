package com.codecool.shop.dao.JDBC_implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.MakeDBConnection;
import com.codecool.shop.model.Product;

import java.util.List;

public class CartDaoJdbc implements CartDao {

    private static CartDaoJdbc instance = null;

    private String user = null;

    public CartDaoJdbc() {
    }

    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }

    private MakeDBConnection makeDBConnection = new MakeDBConnection();


    public void add(Product product){
    }


    public void remove(Product product){
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Product> getAll(){
        return null;
    }
}
