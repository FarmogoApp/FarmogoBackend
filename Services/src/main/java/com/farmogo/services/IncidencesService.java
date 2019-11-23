package com.farmogo.services;



import com.farmogo.dao.IncidenceDao;
import com.farmogo.model.Animal;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceCompleteCheck;

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

    public List<Incidence> getAll(String animalId) {
        // TODO: Verify is animal is in one farm that user has granted acces
        return incidenceDAO.getAll(animalId);
    }


    public List<Incidence> getNotCompleted(String farmId){
        return incidenceDAO.getNotCompleted(farmId);
    }


    public void save(Incidence incidence){
        IncidenceCompleteCheck incidenceCompleteCheck = new IncidenceCompleteCheck();
        incidence.setComplete(incidenceCompleteCheck.check(incidence));
        incidenceDAO.save(incidence);
    }



}