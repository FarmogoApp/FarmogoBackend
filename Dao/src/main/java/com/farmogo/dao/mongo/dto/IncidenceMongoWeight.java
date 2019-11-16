package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.IncidenceType;
import com.farmogo.model.incidences.IncidenceVisitor;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;

@BsonDiscriminator(key = "type", value = "WEIGHT")
public class IncidenceMongoWeight extends IncidenceMongo {



    private int weight;

    public IncidenceMongoWeight() {
        super(IncidenceType.WEIGHT);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
