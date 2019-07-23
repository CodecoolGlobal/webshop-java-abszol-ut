package com.codecool.shop.dao.implementation.registration;



public class User {

    int id;
    String username;
    String email;
    String password;


    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;

    }



//    public boolean isComplete() {
//        return this.status == Status.COMPLETE;
//    }
//
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
}
