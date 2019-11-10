package com.farmono.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Incidences implements Serializable {
    private String uuid;
    private String animalId;
    private int incidenceType;
    private String comments;
    private String userId;
    private Timestamp createdTimestamp;
    private List<Animal> animalList;
    private List<IncidenceTypes>incidenceTypesList;

    public Incidences(){

    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public int getIncidenceType() {
        return incidenceType;
    }

    public void setIncidenceType(int incidenceType) {
        this.incidenceType = incidenceType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<IncidenceTypes> getIncidenceTypesList() {
        return incidenceTypesList;
    }

    public void setIncidenceTypesList(List<IncidenceTypes> incidenceTypesList) {
        this.incidenceTypesList = incidenceTypesList;
    }

}
