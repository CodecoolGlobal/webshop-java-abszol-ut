package com.codecool.shop.model;

public class OrderedProduct {


    private int userID;
    private int productID;
    private int qty;
    private boolean payed;

    public OrderedProduct( int userID, int productID, int qty,boolean payed) {
        this.userID = userID;
        this.productID = productID;
        this.qty = qty;
        this.payed=payed;

    }


    public int getUserID() {
        return userID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQty() {
        return qty;
    }

    public boolean isPayed() {
        return payed;
    }
}
