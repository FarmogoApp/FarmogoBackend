package com.farmogo.dao;

import com.farmogo.model.Divisions;

import java.util.List;

public interface DivisionsDao {
    List<Divisions> getAll();
    void save(Divisions division);
    void delete(Divisions division);
    Divisions get(String id);
}
