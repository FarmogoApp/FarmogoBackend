package com.farmogo.model;

import java.io.Serializable;

public class Races implements Serializable {
    private String uniqueId;
    private String name;

    public Races(){

    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
