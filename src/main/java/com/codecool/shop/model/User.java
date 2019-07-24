package com.codecool.shop.model;

public class User {


    private String userName;
    private String email;
    private String password;

    private AddressInfo addressInfo;
    private PaymentInfo paymentInfo;
    private OrderedProduct orderedProduct;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public OrderedProduct getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(OrderedProduct orderedProduct) {
        this.orderedProduct = orderedProduct;
    }


    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
