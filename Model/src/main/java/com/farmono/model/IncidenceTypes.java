package com.farmono.model;

import java.io.Serializable;

public class IncidenceTypes implements Serializable {
    private  int incidenceType;
    private String description;
    private String icon;

    public IncidenceTypes(){

    }
    public int getIncidenceType() {
        return incidenceType;
    }

    public void setIncidenceType(int incidenceType) {
        this.incidenceType = incidenceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
