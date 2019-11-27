package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.IncidenceType;
import com.farmogo.model.incidences.IncidenceVisitor;
import com.farmogo.model.incidences.TreatmentType;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(key = "type", value = "TREATMENT")
public class IncidenceMongoTreatment extends IncidenceMongo {

    private TreatmentType treatmentType;
    private String medicine;
    private String dose;

    public IncidenceMongoTreatment() {
        super(IncidenceType.TREATMENT);
    }

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

}
