package com.farmogo.services;

import com.farmogo.model.ActionNotPermitted;
import com.farmogo.model.Animal;
import com.farmogo.model.PermissionError;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceDischarge;
import com.farmogo.model.incidences.IncidenceVisitor;

import javax.inject.Inject;

public class IncidenceOnRecoverActions implements IncidenceVisitor {

    @Inject
    AnimalService animalService;

    Animal animal;


    public void action(Incidence incidence) throws PermissionError {
        animal = animalService.get(incidence.getAnimalId());
        incidence.accept(this);
    }


    @Override
    public void visit(IncidenceDischarge obj) throws PermissionError {
        if (animal.getDischargeDate() != null) {
            throw new ActionNotPermitted();
        }
        animal.setDischargeDate(obj.getDate());
        animal.setDischargeCause(obj.getDischargeType().toString());
        animal.setDischargeDestination(obj.getDischargeDestination());
        animal.setDischargeSanitaryRegister(obj.getHealthRegister());
        animalService.save(animal);
    }
}
