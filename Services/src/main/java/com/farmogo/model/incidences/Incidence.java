package com.farmogo.model.incidences;

import java.time.LocalDate;

public abstract class Incidence {
    private String uuid;
    private IncidenceType type;
    private String observations;
    private LocalDate dueDate;
    private boolean done;

    public Incidence(IncidenceType incidenceType) {
        type = incidenceType;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public IncidenceType getType() {
        return type;
    }

    public void setType(IncidenceType type) {
        this.type = type;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public abstract void accept(IncidenceVisitor visitor);
}
