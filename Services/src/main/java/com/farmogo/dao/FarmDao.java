package com.farmogo.dao;

import com.farmogo.model.Farm;

import java.util.List;

public interface FarmDao {
    List<Farm> getAll();
    void save(Farm farm);
    void delete(Farm farm);
    Farm get(String id);
}
