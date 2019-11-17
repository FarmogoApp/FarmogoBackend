package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.IncidenceType;
import com.farmogo.model.incidences.IncidenceVisitor;
import com.farmogo.model.incidences.PregnancyType;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(key = "type", value = "PREGNANCY")
public class IncidenceMongoPregnancy extends IncidenceMongo {
    private PregnancyType pregnancyType;

    public IncidenceMongoPregnancy() {
        super(IncidenceType.PREGNANCY);
    }

    public PregnancyType getPregnancyType() {
        return pregnancyType;
    }

    public void setPregnancyType(PregnancyType pregnancyType) {
        this.pregnancyType = pregnancyType;
    }

}
