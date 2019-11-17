package com.farmogo.dao;

import com.farmogo.model.Animal;
import com.farmogo.model.incidences.Incidence;

import java.util.List;

public interface IncidenceDao {

    void save (Incidence incidence);
    List<Incidence> getAll();
    List<Incidence> getAll(Animal animal);
}
