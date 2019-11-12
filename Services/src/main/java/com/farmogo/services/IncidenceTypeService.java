package com.farmogo.services;

import com.farmogo.dao.IncidenceTypeDAO;
import com.farmono.model.IncidenceTypes;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IncidenceTypeService {

    @Inject
    IncidenceTypeDAO incidenceTypeDAO;

    public List<IncidenceTypes> getAll() {
        return incidenceTypeDAO.getAll();
    }
}