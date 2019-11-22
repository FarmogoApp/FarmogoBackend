package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Race;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class RaceMongo {

    @BsonId
    private ObjectId uuid;
    private String name;

    public RaceMongo() {
    }

    public static RaceMongo convert(Race race) {
        return Mapper.getInstance().map(race, RaceMongo.class);
    }

    public static Race convert(RaceMongo raceMongo) {
        return Mapper.getInstance().map(raceMongo, Race.class);
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
