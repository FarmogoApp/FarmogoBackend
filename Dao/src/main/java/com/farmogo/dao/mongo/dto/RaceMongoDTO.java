package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Race;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;

public class RaceMongoDTO {

    @BsonId
    private String uuid;
    private String name;

    public RaceMongoDTO(){}

    public RaceMongoDTO(Race race){
        uuid = race.getUuid();
        name = race.getName();
    }

    @BsonIgnore
    public Race getObject() {
        Race race = new Race();

        race.setName(this.name);
        race.setUuid(this.uuid);

        return race;
    }

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

}
