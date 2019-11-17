package com.farmogo.services;



import com.farmogo.dao.IncidenceDao;
import com.farmogo.model.incidences.Incidence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IncidencesService {


    @Inject
    IncidenceDao incidenceDAO;

    public List<Incidence> getAll() {
        return incidenceDAO.getAll();
    }

    public void save(Incidence incidence){
        incidenceDAO.save(incidence);
    }



}