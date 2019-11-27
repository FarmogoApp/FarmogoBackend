package com.farmogo.model.incidences;

public interface IncidenceVisitor {

    default void visit(IncidenceDischarge obj) {}
    default void visit(IncidencePregnancy obj) {}
    default void visit(IncidenceTreatment obj) {}
    default void visit(IncidenceWeight obj) {}

}
