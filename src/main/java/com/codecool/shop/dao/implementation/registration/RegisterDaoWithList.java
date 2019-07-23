package com.codecool.shop.dao.implementation.registration;

import java.util.ArrayList;
import java.util.List;

public class RegisterDaoWithList {


    public class TodoDaoImplWithList implements RegisterDao{

        private final List<User> DATA = new ArrayList<>();

        public void add(User user) {
            DATA.add(user);
        }


        public List<User> all() {
            return DATA;
        }
    }

}
