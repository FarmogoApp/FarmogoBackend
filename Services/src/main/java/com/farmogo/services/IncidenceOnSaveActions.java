package com.farmogo.services;

import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.Animal;
import com.farmogo.model.PermissionError;
import com.farmogo.model.incidences.*;

import javax.inject.Inject;
import java.time.LocalDate;

public class IncidenceOnSaveActions implements IncidenceVisitor {

    @Inject
    AnimalService animalService;

    @Inject
    AnimalTypesService animalTypesService;

    @Inject
    FarmService farmService;

    Animal animal;

    public void action(Incidence incidence) throws PermissionError {

        animal = animalService.get(incidence.getAnimalId());

        if (incidence.getFarmId() == null || incidence.getFarmId().isEmpty()) {
            incidence.setFarmId(animal.getFarmId());
        }

        incidence.accept(this);

        IncidenceCompleteCheck incidenceCompleteCheck = new IncidenceCompleteCheck();
        incidence.setComplete(incidenceCompleteCheck.check(incidence));
    }

    @Override
    public void visit(IncidenceDischarge obj) {
        animal.setDischargeDate(obj.getDate());
        animal.setDischargeCause(obj.getDischargeType().toString());
        animal.setDischargeDestination(obj.getDischargeDestination());
        animal.setDischargeSanitaryRegister(obj.getHealthRegister());
        animalService.save(animal);
    }

    @Override
    public void visit(IncidenceBirth incidenceBirth) {
        if(incidenceBirth.getUuid() == null){
            Animal animalChild = new Animal();
            animalChild.setFarmId(incidenceBirth.getFarmId());
            animalChild.setOfficialId(incidenceBirth.getOfficialId());
            animalChild.setBirthDay(incidenceBirth.getBirthDate());
            animalChild.setRaceId(incidenceBirth.getRaceId());
            animalChild.setSex(incidenceBirth.getSex());
            animalChild.setMotherId(incidenceBirth.getAnimalId());
            animalChild.setMotherOfficialId(animal.getOfficialId());
            animalChild.setEnrrollementDate(incidenceBirth.getBirthDate());
            animalChild.setEnrollmentCause("N");
            animalChild.setOrigin("");
            animalChild.setEnrollmentSanitaryRegister("");
            animalChild.setDivisionId(animal.getDivisionId());
            animalChild.setAnimalTypeId(animalTypesService.getAnimalTypeByDescription("Calf").getUuid());
            animalService.save(animalChild);
            farmService.updateAnimalCounter(incidenceBirth.getFarmId());
        }
    }

}

