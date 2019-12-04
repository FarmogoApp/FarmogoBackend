package com.farmogo.services;



import com.farmogo.dao.IncidenceDao;
import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceBirth;
import com.farmogo.model.incidences.IncidenceCompleteCheck;
import com.farmogo.model.incidences.IncidenceType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class IncidencesService {

    @Inject
    FarmService farmService;

    @Inject
    AnimalService animalService;

    @Inject
    AnimalTypesService animalTypesService;

    @Inject
    IncidenceDao incidenceDAO;

    @Inject
    IncidenceOnSaveActions incidenceOnSaveActions;

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
        if(incidence.getType().equals(IncidenceType.BIRTH)){
            saveBirth((IncidenceBirth)incidence);
        }
        incidenceOnSaveActions.action(incidence);
        incidenceDAO.save(incidence);
    }

    private void saveBirth(IncidenceBirth incidenceBirth){
        Animal animal = new Animal();
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