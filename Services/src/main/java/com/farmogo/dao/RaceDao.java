package com.farmogo.dao;

import com.farmogo.model.Race;

import java.util.List;

public interface RaceDao {
    List<Race> getAll();
    void save(Race race);
    void delete(Race race);
    Race get(String id);
}
