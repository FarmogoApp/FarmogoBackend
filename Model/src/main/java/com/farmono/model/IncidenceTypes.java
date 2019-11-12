package com.farmono.model;

import java.io.Serializable;
import java.util.List;

public class IncidenceTypes implements Serializable {
    private  int incidenceType;
    private String description;
    private String icon;
    private List<Incidences> incidencesList;

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
    public List<Incidences> getIncidencesList() {
        return incidencesList;
    }

    public void setIncidencesList(List<Incidences> incidencesList) {
        this.incidencesList = incidencesList;
    }

}
