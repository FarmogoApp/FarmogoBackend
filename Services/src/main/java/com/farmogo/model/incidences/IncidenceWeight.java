package com.farmogo.model.incidences;

import com.farmogo.model.PermissionError;

public class IncidenceWeight extends Incidence {

    private int weight;

    public IncidenceWeight() {
        super(IncidenceType.WEIGHT);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void accept(IncidenceVisitor visitor) throws PermissionError {
        visitor.visit(this);
    }
}
