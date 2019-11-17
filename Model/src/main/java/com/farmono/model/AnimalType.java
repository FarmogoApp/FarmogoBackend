package com.farmono.model;

import java.io.Serializable;

public class AnimalType implements Serializable {

    private int animalType;
    private String description;
    private String icon;

    public AnimalType() {
    }

    public int getAnimalType() {
        return animalType;
    }

    public void setAnimalType(int animalType) {
        this.animalType = animalType;
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
