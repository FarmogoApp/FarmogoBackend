package com.farmogo.services;

import com.farmogo.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

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

    public User create(User user){
        // userDao.create(user);
        user.setUuid(UUID.randomUUID().toString());
        return user;
    }


}

