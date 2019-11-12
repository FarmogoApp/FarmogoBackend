package com.farmogo.services;

import com.farmogo.dao.IncidencesDAO;
import com.farmono.model.Incidences;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IncidencesService {

    @Inject
    IncidencesDAO incidencesDAO;

    public List<Incidences> getAll() {
        return incidencesDAO.getAll();
    }
}