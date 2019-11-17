package com.farmogo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Incidence implements Serializable {
    private String uuid;
    private String animalId;
    private String comments;
    private String userId;
    private Timestamp createdTimestamp;
    private IncidenceType incidenceType;

    public Incidence(){

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


    public IncidenceType getIncidenceType() {
        return incidenceType;
    }

    public void setIncidenceType(IncidenceType incidenceTypeList) {
        this.incidenceType = incidenceTypeList;
    }

}
