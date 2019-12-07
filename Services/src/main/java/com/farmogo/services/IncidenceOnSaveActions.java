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
        if (animal.getDischargeDate() == null) {
            animal.setDischargeDate(LocalDate.now());
            animalService.save(animal);
        }
    }

    @Override
    public void visit(IncidenceBirth incidenceBirth) throws AccessNotAllowed {
        animal = new Animal();
        animal.setFarmId(incidenceBirth.getFarmId());
        animal.setOfficialId(incidenceBirth.getOfficialId());
        animal.setBirthDay(incidenceBirth.getBirthDate());
        animal.setRaceId(incidenceBirth.getRaceId());
        animal.setSex(incidenceBirth.getSex());
        animal.setMotherId(incidenceBirth.getAnimalId());
        animal.setMotherOfficialId(animalService.get(incidenceBirth.getAnimalId()).getOfficialId());
        animal.setEnrrollementDate(incidenceBirth.getBirthDate());
        animal.setEnrollmentCause("N");
        animal.setOrigin("");
        animal.setEnrollmentSanitaryRegister("");
        animal.setDivisionId(animalService.get(incidenceBirth.getAnimalId()).getDivisionId());
        animal.setAnimalTypeId(animalTypesService.getAnimalTypeByDescription("Calf").getUuid());
        animalService.save(animal);
        farmService.updateAnimalCounter(incidenceBirth.getFarmId());
    }

}

