package com.farmono.model;

import java.io.Serializable;
import java.util.List;

public class Building implements Serializable {
    private String uuid;
    private String idFarm;
    private String name;
     private List<Divisions> divisionsList;
     private Farm farm;
    public Building(){

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdFarm() {
        return idFarm;
    }

    public void setIdFarm(String idFarm) {
        this.idFarm = idFarm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Divisions> getDivisionsList() {
        return divisionsList;
    }

    public void setDivisionsList(List<Divisions> divisionsList) {
        this.divisionsList = divisionsList;
    }
    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
