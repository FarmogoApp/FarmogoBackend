package com.farmogo.model;




import java.io.Serializable;
import java.util.UUID;


public class AnimalType implements Serializable {


    private String animalType;
    private String description;
    private String icon;

    public AnimalType() {
        animalType = UUID.randomUUID().toString();
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
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
