package com.codecool.shop.dao;

import com.codecool.shop.model.AddressInfo;

import java.util.List;

public interface AddressInfoDao {

    void add(AddressInfo addressInfo);
    AddressInfo find(int id);
    void remove(int id);

    List<AddressInfo> getAll();
}
