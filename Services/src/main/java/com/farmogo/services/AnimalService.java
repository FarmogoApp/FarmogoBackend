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

    public List<Animal> getAnimalsByFarmId(String farmId) {
        return animalDao.getAllAnimalsByFarmId(farmId);
    }
    public List<Animal> getCurrentAnimalsByFarmId(String farmId){
        return animalDao.getCurrentAnimalsByFarmId(farmId);
    }
    public List<Animal> getDischagedAnimalsByFarmId(String farmId) {
        return animalDao.getDischargedAnimalsByFarmId(farmId);
    }

    public Animal get(String id) {
        return animalDao.get(id);
    }

    public Animal save(Animal animal){
        return animalDao.save(animal);
    }

    public void delete(Animal animal) {
        Animal animalToDelete = animalDao.get(animal.getUuid());
        animalDao.delete(animalToDelete);
    }

    public Animal getAnimalById(String animalId){
        return animalDao.get(animalId);
    }


    public List<Animal> getByRace(String raceId) {
        return animalDao.getByRace(raceId);
    }

    public List<Animal> getByAnimalType(String animalTypeId) {
        return animalDao.getByAnimalType(animalTypeId);
    }
}


