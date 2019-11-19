package com.farmogo.dao.mongo.dto;

import com.farmogo.model.AnimalType;
import com.farmogo.model.MarketHistory;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;

public class MarketHistoryMongo {

    @BsonId
    private Date date;
    private int animalTyupe;
    private BigDecimal price;
    private AnimalType animalType;

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

    public static MarketHistory convert(MarketHistoryMongo marketHistoryMongo){
        if (marketHistoryMongo == null) return null;
        return Mapper.getInstance().map(marketHistoryMongo, MarketHistory.class);
    }

    public static MarketHistoryMongo convert(MarketHistory marketHistory){
        if (marketHistory == null) return null;
        return Mapper.getInstance().map(marketHistory, MarketHistoryMongo.class);
    }

}
