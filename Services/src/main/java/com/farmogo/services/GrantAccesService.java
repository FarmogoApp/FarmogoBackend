package com.farmogo.services;

import com.farmogo.dao.GrantAccesDAO;
import com.farmono.model.GrantAcces;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class GrantAccesService {

    @Inject
    GrantAccesDAO grantAccesDAO;

    public List<GrantAcces> getAll() {
        return grantAccesDAO.getAll();
    }
}