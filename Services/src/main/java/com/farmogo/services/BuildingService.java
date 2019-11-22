package com.farmogo.services;

import com.farmogo.model.Building;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BuildingService {

    //@Inject
    //BuildingDAO buildingDAO;

    public List<Building> getAll() {
        return null;
        //return buildingDAO.getAll();
    }




}
