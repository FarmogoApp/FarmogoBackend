package com.farmogo.dao;

import com.farmogo.model.AnimalType;

import java.util.List;

public interface AnimalTypeDao {
    List<AnimalType> getAll();
    void save(AnimalType animalType);
    void delete(AnimalType animalType);
    AnimalType get(String id);
}
