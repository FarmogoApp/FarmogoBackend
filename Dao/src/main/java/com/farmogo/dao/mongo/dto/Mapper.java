package com.farmogo.dao.mongo.dto;

import com.farmogo.dao.mongo.ObjectIdConverter;
import com.farmogo.model.AnimalType;
import com.farmogo.model.Building;
import com.farmogo.model.Division;
import com.farmogo.model.Farm;
import com.farmogo.model.incidences.IncidenceGetoff;
import com.farmogo.model.incidences.IncidencePregnancy;
import com.farmogo.model.incidences.IncidenceTreatment;
import com.farmogo.model.incidences.IncidenceWeight;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class Mapper {

    private static Mapper mapper;

    private MapperFacade mapperFacade;

    private Mapper(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new ObjectIdConverter());
        mapperFactory.classMap(AnimalType.class, AnimalTypeMongo.class).byDefault().register();
        mapperFactory.classMap(IncidenceGetoff.class, IncidenceMongoGetoff.class)
                .field("animal.uuid", "animalId")
                .field("createdBy.uuid", "createdBy")
                .field("farm.uuid", "farm")
                .byDefault()
                .register();
        mapperFactory.classMap(IncidencePregnancy.class, IncidenceMongoPregnancy.class)
                .field("animal.uuid", "animalId")
                .field("createdBy.uuid", "createdBy")
                .field("farm.uuid", "farm")
                .byDefault()
                .register();
        mapperFactory.classMap(IncidenceTreatment.class, IncidenceMongoTreatment.class)
                .field("animal.uuid", "animalId")
                .field("createdBy.uuid", "createdBy")
                .field("farm.uuid", "farm")
                .byDefault()
                .register();
        mapperFactory.classMap(IncidenceWeight.class, IncidenceMongoWeight.class)
                .field("animal.uuid", "animalId")
                .field("createdBy.uuid", "createdBy")
                .field("farm.uuid", "farm")
                .byDefault()
                .register();
        mapperFactory.classMap(Farm.class, FarmMongo.class).byDefault().register();
        mapperFactory.classMap(Building.class, BuildingMongo.class).byDefault().register();
        mapperFactory.classMap(Division.class, DivisionMongo.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public static MapperFacade getInstance(){
        if (mapper == null){
            mapper = new Mapper();
        }
        return mapper.mapperFacade;
    }
}
