package com.farmogo.services;

import com.farmogo.model.GrantAcces;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GrantAccesService {


    //@Inject
    //GrantAccesDAO grantAccesDAO;

    public List<GrantAcces> getAll() {
        return null;
        //return grantAccesDAO.getAll();
    }


}