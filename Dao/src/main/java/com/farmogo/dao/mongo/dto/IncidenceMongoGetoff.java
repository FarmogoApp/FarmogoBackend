package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.GetoffType;
import com.farmogo.model.incidences.IncidenceType;
import com.farmogo.model.incidences.IncidenceVisitor;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(key = "type", value = "GETOFF")
public class IncidenceMongoGetoff extends IncidenceMongo {

    private GetoffType getoffType;
    private String healthRegister;

    public IncidenceMongoGetoff() {
        super(IncidenceType.GETOFF);
    }

    public GetoffType getGetoffType() {
        return getoffType;
    }

    public void setGetoffType(GetoffType getoffType) {
        this.getoffType = getoffType;
    }

    public String getHealthRegister() {
        return healthRegister;
    }

    public void setHealthRegister(String healthRegister) {
        this.healthRegister = healthRegister;
    }


}
