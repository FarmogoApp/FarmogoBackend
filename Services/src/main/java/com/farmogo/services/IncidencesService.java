package com.farmogo.services;

import com.farmogo.model.Incidence;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class IncidencesService {

    /*
    @Inject
    IncidencesDAO incidencesDAO;
    */
    public List<Incidence> getAll() {
        return null;
        //return incidencesDAO.getAll();
    }


}