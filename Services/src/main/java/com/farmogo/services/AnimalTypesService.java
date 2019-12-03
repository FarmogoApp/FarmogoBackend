package com.farmogo.services;


import com.farmogo.dao.AnimalTypeDao;
import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class AnimalTypesService {

    @Inject
    AnimalTypeDao animalTypeDAO;

    @Inject
    AnimalService animalService;

    public List<AnimalType> getAll() {
        return animalTypeDAO.getAll();
    }

    public AnimalType get(String id) {
        return animalTypeDAO.get(id);
    }

    public AnimalType getAnimalTypeByDescription(String description) {
        return animalTypeDAO.getAnimalTypeByDescription(description);
    }

    public AnimalType save(AnimalType animalType) {
        return animalTypeDAO.save(animalType);
    }

    public void delete(AnimalType animalType) throws HasRelationatedDataException {
        List<Animal> animals = animalService.getByAnimalType(animalType.getUuid());
        if (!animals.isEmpty()){
            throw new HasRelationatedDataException();
        }
        animalTypeDAO.delete(animalType);
    }
}
