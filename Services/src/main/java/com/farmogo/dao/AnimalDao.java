package com.farmogo.dao;

import com.farmogo.model.Animal;

import java.util.List;

public interface AnimalDao {
    List<Animal> getAll(List<String> farmsId);
    List<Animal> getCurrentAnimalsByFarmId(String farmId);
    List<Animal> getAllAnimalsByFarmId(String farmId);
    List<Animal> getDischargedAnimalsByFarmId(String farmId);
    Animal save(Animal animal);
    void delete(Animal animal);
    Animal get(String id);
    List<Animal> getByRace(String raceId);
    List<Animal> getByAnimalType(String animalTypeId);
    List<Animal> getCurrentAnimals();
}

