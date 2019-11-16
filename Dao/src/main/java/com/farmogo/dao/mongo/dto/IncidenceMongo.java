package com.farmogo.dao.mongo.dto;

import com.farmogo.model.AnimalType;
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
    private LocalDate dueDate;
    private boolean done;
    private LocalDateTime created;

    public IncidenceMongo(IncidenceType incidenceType) {
        type = incidenceType;
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

    public static IncidenceMongo convert(Incidence incidence) {
        return Mapper.getInstance().map(incidence, IncidenceMongo.class);
    }

    public static Incidence convert(IncidenceMongo incidenceMongo) {
        return Mapper.getInstance().map(incidenceMongo, Incidence.class);
    }

}
