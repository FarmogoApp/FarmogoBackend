package com.farmogo.model.incidences;

public interface IncidenceVisitor {

    void visit(IncidenceDischarge obj);
    void visit(IncidencePregnancy obj);
    void visit(IncidenceTreatment obj);
    void visit(IncidenceWeight obj);

}
