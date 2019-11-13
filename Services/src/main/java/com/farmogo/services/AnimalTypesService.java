package com.farmogo.services;

import com.farmogo.dao.AnimalTypeDAO;
import com.farmogo.model.AnimalType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class AnimalTypesService {

    @Inject
    AnimalTypeDAO animalTypeDAO;

    public List<AnimalType> getAll() {
        return animalTypeDAO.getAll();
    }

    public void save(AnimalType animalType){
        animalTypeDAO.save(animalType);
    }

    public void delete(AnimalType animalType) {
        AnimalType animalType1 = animalTypeDAO.get(animalType.getAnimalType());
        if (animalType1.getDescription().startsWith("n")) return;
                animalTypeDAO.delete(animalType);
    }
}
