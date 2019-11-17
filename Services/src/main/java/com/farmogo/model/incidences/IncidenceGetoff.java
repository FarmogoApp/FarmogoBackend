package com.farmogo.model.incidences;

public class IncidenceGetoff extends Incidence {

    private GetoffType getoffType;
    private String healthRegister;

    public IncidenceGetoff() {
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


    @Override
    public void accept(IncidenceVisitor visitor) {
        visitor.visit(this);
    }
}
