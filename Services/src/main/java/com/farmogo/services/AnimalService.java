package com.farmogo.services;

import com.farmogo.dao.AnimalDao;
import com.farmogo.model.Animal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class AnimalService {

    @Inject
    AnimalDao animalDao;

    public List<Animal> getAll() {
        return animalDao.getAll();
    }

    public List<Animal> getAnimalsByFarmId(String farmId){
        return animalDao.getAnimalsByFarmId(farmId);
    }

    public void save(Animal animal){
        animalDao.save(animal);
    }

    public void delete(Animal animal) {
        Animal animalToDelete = animalDao.get(animal.getUuid());
        animalDao.delete(animalToDelete);
    }

    public Animal getAnimalById(String animalId){
        return animalDao.get(animalId);
    }
}


