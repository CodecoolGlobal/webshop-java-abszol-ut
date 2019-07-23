package com.codecool.shop.model;

public class AddressInfo {

    private int ID;
    private int userID;
    private String fullName;
    private String address;
    private String city;
    private String state;
    private int zip;

    public AddressInfo(int ID, int userID, String fullName, String address, String city, String state, int zip) {
        this.ID = ID;
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

}
