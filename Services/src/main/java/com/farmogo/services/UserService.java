package com.farmogo.services;

import com.farmogo.dao.UserDao;
import com.farmogo.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Stateless
public class UserService {

    @Inject
    GlobalSessionService globalSessionService;

    @Inject
    UserDao userDao;


    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getCurrentUser(){
        return globalSessionService.getUser();
    }

    public User save(User user){
        return userDao.save(user);
    }


}

