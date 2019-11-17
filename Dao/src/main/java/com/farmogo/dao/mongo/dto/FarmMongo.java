package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Farm;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

public class FarmMongo implements Serializable {

    @BsonId
    private ObjectId uuid;
    private String name;
    private String officialId;
    private List<BuildingMongo> buildings;

    public static FarmMongo convert(Farm farm) {
        return Mapper.getInstance().map(farm, FarmMongo.class);
    }

    public static Farm convert(FarmMongo farmMongo) {
        return Mapper.getInstance().map(farmMongo, Farm.class);
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

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public List<BuildingMongo> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingMongo> buildings) {
        this.buildings = buildings;
    }
}