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


    public User getByFirebaseUuid(String firebaseUuid) {
        return userDao.getByFirebaseUuid(firebaseUuid);
    }

    public void setCurrentUser(User user) {
        globalSessionService.setUser(user);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}

