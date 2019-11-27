package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceType;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;


@BsonDiscriminator(key = "type", value = "##")
public abstract class IncidenceMongo {
    @BsonId
    private ObjectId uuid;
    @BsonIgnore
    private IncidenceType type;
    private String observations;
    private LocalDate date;
    private LocalDate dueDate;
    private boolean done;
    private boolean complete;
    private LocalDateTime created;
    private ObjectId createdBy;
    private ObjectId animalId;
    private ObjectId farmId;
    private LocalDate removeDate;
    private String removeReason;

    public IncidenceMongo(IncidenceType incidenceType) {
        type = incidenceType;
    }

    public static IncidenceMongo convert(Incidence incidence) {
        return Mapper.getInstance().map(incidence, IncidenceMongo.class);
    }

    public static Incidence convert(IncidenceMongo incidenceMongo) {
        return Mapper.getInstance().map(incidenceMongo, Incidence.class);
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public ObjectId getAnimalId() {
        return animalId;
    }

    public void setAnimalId(ObjectId animalId) {
        this.animalId = animalId;
    }

    public ObjectId getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ObjectId createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public ObjectId getFarmId() {
        return farmId;
    }

    public void setFarmId(ObjectId farmId) {
        this.farmId = farmId;
    }

    public LocalDate getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(LocalDate removeDate) {
        this.removeDate = removeDate;
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }

}
