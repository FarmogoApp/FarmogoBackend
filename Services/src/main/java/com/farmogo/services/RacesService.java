package com.farmogo.services;


import com.farmogo.dao.RacesDAO;
import com.farmono.model.Races;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RacesService {

    @Inject
    RacesDAO racesDAO;

    public List<Races> getAll() {
        return racesDAO.getAll();
    }
}