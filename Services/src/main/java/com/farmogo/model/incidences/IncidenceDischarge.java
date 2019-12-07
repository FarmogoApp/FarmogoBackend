package com.farmogo.model.incidences;

import com.farmogo.model.PermissionError;

public class IncidenceDischarge extends Incidence {

    private DischargeType dischargeType;
    private String healthRegister;

    public IncidenceDischarge() {
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


    @Override
    public void accept(IncidenceVisitor visitor) throws PermissionError {
        visitor.visit(this);
    }
}
