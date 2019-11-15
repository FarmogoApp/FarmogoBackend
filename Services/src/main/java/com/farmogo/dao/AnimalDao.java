package com.farmogo.dao;

import com.farmogo.model.Animal;

import java.util.List;

public interface AnimalDao {
    List<Animal> getAll();
    void save(Animal animal);
    void delete(Animal animal);
    Animal get(String id);
}
