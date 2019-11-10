package com.farmono.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MarketHistory implements Serializable {
    private Date date;
    private int animalTyupe;
    private BigDecimal price;
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
}
