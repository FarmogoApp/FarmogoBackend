package com.farmogo.model.incidences;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Incidence {
    private String uuid;
    private IncidenceType type;
    private String observations;
    private LocalDate dueDate;
    private boolean done;
    private LocalDateTime created;

    public Incidence(IncidenceType incidenceType) {
        type = incidenceType;
        created = LocalDateTime.now();
    }

    public IncidenceType getType() {
        return type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public abstract void accept(IncidenceVisitor visitor);
}
