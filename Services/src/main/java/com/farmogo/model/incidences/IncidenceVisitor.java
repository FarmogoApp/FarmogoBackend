package com.farmogo.model.incidences;

import com.farmogo.model.PermissionError;

public interface IncidenceVisitor {

    default void visit(IncidenceDischarge obj) throws PermissionError {
    }

    default void visit(IncidencePregnancy obj) throws PermissionError {
    }

    default void visit(IncidenceTreatment obj) throws PermissionError {
    }

    default void visit(IncidenceWeight obj) throws PermissionError {
    }

    default void visit(IncidenceBirth obj) throws PermissionError {
    }

}
