package com.farmogo.services;

import com.farmogo.dao.AdminDao;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AdminService {

    @Inject
    AdminDao adminDao;

    public void clearDatabase(){
        adminDao.clearDatabase();
    }

}
