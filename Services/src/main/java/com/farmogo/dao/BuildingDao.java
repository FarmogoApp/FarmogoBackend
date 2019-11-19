package com.farmogo.dao;

import com.farmogo.model.Building;

import java.util.List;

public interface BuildingDao {
    List<Building> getAll();
    void save(Building building);
    void delete(Building building);
    Building get(String id);
}
