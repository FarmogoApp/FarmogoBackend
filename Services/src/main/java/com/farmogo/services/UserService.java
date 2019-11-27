package com.farmogo.services;

import com.farmogo.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    GlobalSessionService globalSessionService;

    public List<User> getAll() {
        return null;
        //return userDAO.getAll();
    }

    public User getCurrentUser(){
        return globalSessionService.getUser();
    }


}

