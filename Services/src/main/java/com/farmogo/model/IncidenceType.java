package com.farmogo.model;

import java.io.Serializable;
import java.util.List;

public class IncidenceType implements Serializable {
    
    private  int incidenceType;
    private String description;
    private String icon;
    private List<Incidence> incidencesList;


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
    public List<Incidence> getIncidencesList() {
        return incidencesList;
    }

    public void setIncidencesList(List<Incidence> incidencesList) {
        this.incidencesList = incidencesList;
    }

}
