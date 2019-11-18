package com.farmogo.model.incidences;

import com.farmogo.model.Animal;
import com.farmogo.model.Farm;
import com.farmogo.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Incidence {
    private String uuid;
    private IncidenceType type;
    private String observations;
    private LocalDate dueDate;
    private boolean done;
    private LocalDateTime created;
    private User createdBy;
    private Animal animal;
    private Farm farm;

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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public abstract void accept(IncidenceVisitor visitor);
}
