package com.farmogo.dao.mongo.dto;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class AnimalTypeMongo {

    @BsonId
    private ObjectId animalType;
    private String description;
    private String icon;


    public ObjectId getAnimalType() {
        return animalType;
    }

    public void setAnimalType(ObjectId animalType) {
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
