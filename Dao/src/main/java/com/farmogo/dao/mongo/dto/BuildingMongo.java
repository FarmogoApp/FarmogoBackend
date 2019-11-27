package com.farmogo.dao.mongo.dto;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

public class BuildingMongo implements Serializable {
    private ObjectId uuid;
    private String name;
    private List<DivisionMongo> divisions;


    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DivisionMongo> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<DivisionMongo> divisions) {
        this.divisions = divisions;
    }
}
