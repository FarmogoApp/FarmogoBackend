package com.farmogo.model;

import java.io.Serializable;
import java.util.List;

public class Farm implements Serializable {
    private String uuid;
    private String name;
    private String officialId;
    private List<Building> buildings;
    private AnimalCounter animalCounter;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public AnimalCounter getAnimalCounter() {
        return animalCounter;
    }

    public void setAnimalCounter(AnimalCounter animalCounter) {
        this.animalCounter = animalCounter;
    }
}
