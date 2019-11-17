package com.farmogo.dao;

import com.farmogo.model.Farm;

import java.util.List;

public interface FarmDao {
    List<Farm> getFarmByUser(String userId);
    List<Farm> getAll();
    Farm get(String id);
    void save(Farm farm);
}
