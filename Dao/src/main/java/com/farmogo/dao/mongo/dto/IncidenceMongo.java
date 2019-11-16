package com.farmogo.dao.mongo.dto;

import com.farmogo.dao.mongo.ObjectIdConverter;
import com.farmogo.model.incidences.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

import java.time.LocalDate;


@BsonDiscriminator(key = "type",value = "##")
public abstract class IncidenceMongo {
    @BsonId
    private ObjectId uuid;
    @BsonIgnore
    private IncidenceType type;
    private String observations;
    private LocalDate dueDate;
    private boolean done;

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


    private static MapperFacade mapperFacade;
    static{
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new ObjectIdConverter());
        mapperFactory.classMap(IncidenceGetoff.class, IncidenceMongoGetoff.class)
                .byDefault()
                .register();
        mapperFactory.classMap(IncidencePregnancy.class, IncidenceMongoPregnancy.class)
                .byDefault()
                .register();
        mapperFactory.classMap(IncidenceTreatment.class, IncidenceMongoTreatment.class)
                .byDefault()
                .register();
        mapperFactory.classMap(IncidenceWeight.class, IncidenceMongoWeight.class)
                .byDefault()
                .register();
       mapperFacade = mapperFactory.getMapperFacade();
    }

    public static IncidenceMongo convert(Incidence incidence){
        return mapperFacade.map(incidence, IncidenceMongo.class);
    }

    public static Incidence convert(IncidenceMongo incidenceMongo){
        return mapperFacade.map(incidenceMongo, Incidence.class);
    }


}
