package com.farmogo.dao;

import com.farmogo.model.Races;

import java.util.List;

public interface RacesDao {
    List<Races> getAll();
    void save(Races race);
    void delete(Races race);
    Races get(String id);
}
