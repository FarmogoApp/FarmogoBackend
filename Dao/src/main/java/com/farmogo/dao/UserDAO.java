package com.farmogo.dao;

import com.farmogo.model.User;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO {

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();

        }
        return userList;
    }
}