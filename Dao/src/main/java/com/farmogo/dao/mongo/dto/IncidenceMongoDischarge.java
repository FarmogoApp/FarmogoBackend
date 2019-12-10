package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.DischargeType;
import com.farmogo.model.incidences.IncidenceType;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(key = "type", value = "DISCHARGE")
public class IncidenceMongoDischarge extends IncidenceMongo {

    private DischargeType dischargeType;
    private String healthRegister;
    private String dischargeDestination;

    public IncidenceMongoDischarge() {
        super(IncidenceType.DISCHARGE);
    }

    public DischargeType getDischargeType() {
        return dischargeType;
    }

    public void setDischargeType(DischargeType dischargeType) {
        this.dischargeType = dischargeType;
    }

    public String getHealthRegister() {
        return healthRegister;
    }

    public void setHealthRegister(String healthRegister) {
        this.healthRegister = healthRegister;
    }


    public String getDischargeDestination() {
        return dischargeDestination;
    }

    public void setDischargeDestination(String dischargeDestination) {
        this.dischargeDestination = dischargeDestination;
    }
}
