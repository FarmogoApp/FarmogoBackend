package com.farmogo.services;


import com.farmogo.dao.IncidenceDao;
import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.PermissionError;
import com.farmogo.model.incidences.Incidence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IncidencesService {

    @Inject
    IncidenceDao incidenceDAO;

    @Inject
    IncidenceOnSaveActions incidenceOnSaveActions;

    public List<Incidence> getAll() {
        return incidenceDAO.getAll();
    }

    public List<Incidence> getAll(String animalId) {
        return incidenceDAO.getAll(animalId);
    }


    public List<Incidence> getNotCompleted(String farmId) {
        return incidenceDAO.getNotCompleted(farmId);
    }


    public List<Incidence> getLast(String farmId, int limit) {
        return incidenceDAO.getLast(farmId, limit);
    }


    public void save(Incidence incidence) throws PermissionError {
        incidenceOnSaveActions.action(incidence);
        incidenceDAO.save(incidence);
    }

    public List<Incidence> getByAnimal(String animalId, int skip, int limit) {
        return incidenceDAO.getByAnimalId(animalId, skip, limit);
    }
}