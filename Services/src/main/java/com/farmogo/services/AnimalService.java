package com.farmogo.services;

import com.farmogo.dao.AnimalDao;
import com.farmogo.model.Animal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class AnimalService {

    @Inject
    AnimalDao animalTypeDAO;

    public List<Animal> getAll() {
        return animalTypeDAO.getAll();
    }

    public List<Animal> getAnimalsByFarmId(String farmId){
        return animalTypeDAO.getAnimalsByFarmId(farmId);
    }

    public void save(Animal animal){
        animalTypeDAO.save(animal);
    }

    public void delete(Animal animal) {
        Animal animalToDelete = animalTypeDAO.get(animal.getUuid());
        animalTypeDAO.delete(animalToDelete);
    }
}


