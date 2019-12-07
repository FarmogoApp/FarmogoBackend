package com.farmogo.dao;

import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;

import java.util.List;

public interface AnimalTypeDao {
    List<AnimalType> getAll();
    AnimalType save(AnimalType animalType);
    void delete(AnimalType animalType);
    AnimalType get(String id);
    AnimalType getAnimalTypeByDescription(String description);
}
