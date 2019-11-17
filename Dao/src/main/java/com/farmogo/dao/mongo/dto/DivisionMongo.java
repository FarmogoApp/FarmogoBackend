package com.farmogo.dao.mongo.dto;



import org.bson.types.ObjectId;

import java.io.Serializable;

public class DivisionMongo implements Serializable {
    private ObjectId uuid;
    private String name;

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
