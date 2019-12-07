package com.farmogo.dao;

import com.farmogo.model.AnimalType;
import com.farmogo.model.Farm;

import java.util.List;

public interface FarmDao {
    List<Farm> getFarmByOwner(String userId);
    List<Farm> getAll();
    Farm get(String id);
    void delete(Farm farm);
    Farm save(Farm farm);
    void updateAnimalCounter(String id);
}
