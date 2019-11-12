package com.farmogo.dao;

import com.farmono.model.Building;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BuildingDAO {

    public List<Building> getAll() {
        List<Building> buildingList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Building building = new Building();

        }
        return buildingList;
    }
}