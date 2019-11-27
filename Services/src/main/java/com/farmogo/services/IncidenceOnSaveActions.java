package com.farmogo.services;

import com.farmogo.model.Animal;
import com.farmogo.model.incidences.*;

import javax.inject.Inject;
import java.time.LocalDate;

public class IncidenceOnSaveActions implements IncidenceVisitor {

    @Inject
    AnimalService animalService;

    Animal animal;

    public void action(Incidence incidence){

        animal = animalService.get(incidence.getAnimalId());

        if (incidence.getFarmId()== null  || incidence.getFarmId().isEmpty()){
            incidence.setFarmId(animal.getFarmId());
        }

        incidence.accept(this);

        IncidenceCompleteCheck incidenceCompleteCheck = new IncidenceCompleteCheck();
        incidence.setComplete(incidenceCompleteCheck.check(incidence));
    }

    @Override
    public void visit(IncidenceDischarge obj) {
        if (animal.getDischargeDate()==null) {
            animal.setDischargeDate(LocalDate.now());
            animalService.save(animal);
        }
    }

}
