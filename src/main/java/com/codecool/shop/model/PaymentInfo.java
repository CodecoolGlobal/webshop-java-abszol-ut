package com.codecool.shop.model;

public class PaymentInfo {

    private int ID;
    private int userID;
    private String name;
    private int cardNumber;
    private int month;
    private int year;

    public PaymentInfo(int ID, int userID, String name, int cardNumber, int month, int year) {
        this.ID = ID;
        this.userID = userID;
        this.name = name;
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
