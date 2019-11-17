package com.farmogo.services;

import com.farmogo.model.IncidenceType;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class IncidenceTypeService {


    //@Inject
    //IncidenceTypeDAO incidenceTypeDAO;

    public List<IncidenceType> getAll() {
        return null;
        //return incidenceTypeDAO.getAll();
    }


}