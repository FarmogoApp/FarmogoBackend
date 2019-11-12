package com.farmogo.services;

import com.farmogo.dao.DivisionDAO;
import com.farmono.model.Divisions;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DivisionService {

    @Inject
    DivisionDAO divisionsDAO;

    public List<Divisions> getAll() {
        return divisionsDAO.getAll();
    }
}