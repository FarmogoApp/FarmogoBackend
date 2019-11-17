package com.farmogo.model;

import java.io.Serializable;
import java.util.List;

public class Building implements Serializable {
    private String uuid;
    private String name;
    private List<Division> divisions;


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

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

}
