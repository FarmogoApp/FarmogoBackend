package com.farmogo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MarketHistory implements Serializable {
    private Date date;
    private int animalTyupe;
    private BigDecimal price;
    private AnimalType animalType;
    public MarketHistory(){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAnimalTyupe() {
        return animalTyupe;
    }

    public void setAnimalTyupe(int animalTyupe) {
        this.animalTyupe = animalTyupe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
