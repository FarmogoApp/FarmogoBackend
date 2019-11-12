package com.farmogo.services;

import com.farmogo.dao.BuildingDAO;
import com.farmono.model.Building;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BuildingService {

    @Inject
    BuildingDAO buildingDAO;

    public List<Building> getAll() {
        return buildingDAO.getAll();
    }
}
