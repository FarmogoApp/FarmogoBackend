package com.farmogo.dao.mongo.dto;

import com.farmogo.model.AnimalType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;

public class AnimalTypeMongo {

    @BsonId
    private String animalType;
    private String description;
    private String icon;

    public AnimalTypeMongo() {
    }

    public AnimalTypeMongo(AnimalType animalType){
        this.animalType = animalType.getAnimalType();
        this.description = animalType.getDescription();
        this.icon = animalType.getIcon();
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

    @BsonIgnore
    public AnimalType getObject(){
        AnimalType animalType = new AnimalType();
        animalType.setAnimalType(this.animalType);
        animalType.setDescription(this.description);
        animalType.setIcon(this.icon);
        return animalType;
    }
}
