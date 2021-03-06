package com.farmogo.services;


import com.farmogo.dao.IncidenceDao;
import com.farmogo.model.PermissionError;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceDischarge;
import com.farmogo.model.incidences.IncidenceVisitor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class IncidencesService {

    @Inject
    IncidenceDao incidenceDAO;

    @Inject
    IncidenceOnSaveActions incidenceOnSaveActions;

    @Inject
    IncidenceOnRemoveActions incidenceOnRemoveActions;

    @Inject
    IncidenceOnRecoverActions incidenceOnRecoverActions;

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

    public Incidence save(Incidence incidence) throws PermissionError {
        incidenceOnSaveActions.action(incidence);
        return incidenceDAO.save(incidence);
    }

    public List<Incidence> getByAnimal(String animalId, int skip, int limit) {
        return incidenceDAO.getByAnimalId(animalId, skip, limit);
    }

    public void remove(Incidence incidence) throws PermissionError {
        incidenceOnRemoveActions.action(incidence);
        incidence.setRemoveDate(LocalDate.now());
        incidenceDAO.save(incidence);
    }

    public void recover(Incidence incidence) throws PermissionError {
        incidenceOnRecoverActions.action(incidence);
        incidence.setRemoveDate(null);
        incidence.setRemoveReason(null);
        incidenceDAO.save(incidence);
    }
}