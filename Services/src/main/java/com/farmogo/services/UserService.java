package com.farmogo.services;

import com.farmogo.dao.UserDAO;
import com.farmono.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    public List<User> getAll() {
        return userDAO.getAll();
    }
}

