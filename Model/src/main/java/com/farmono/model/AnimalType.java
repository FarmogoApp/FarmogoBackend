package com.farmono.model;

import java.io.Serializable;
import java.util.List;

public class AnimalType implements Serializable {

    private int animalType;
    private String description;
    private String icon;
     private MarketHistory marketHistory;

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
    public MarketHistory getMarketHistory() {
        return marketHistory;
    }

    public void setMarketHistory(MarketHistory marketHistory) {
        this.marketHistory = marketHistory;
    }

}
