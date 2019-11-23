package com.farmogo.dao.mongo.dto;

import com.farmogo.dao.mongo.ObjectIdConverter;
import com.farmogo.model.*;
import com.farmogo.model.incidences.IncidenceDischarge;
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
        mapperFactory.classMap(IncidenceDischarge.class, IncidenceMongoDischarge.class).byDefault().register();
        mapperFactory.classMap(IncidencePregnancy.class, IncidenceMongoPregnancy.class).byDefault().register();
        mapperFactory.classMap(IncidenceTreatment.class, IncidenceMongoTreatment.class).byDefault().register();
        mapperFactory.classMap(IncidenceWeight.class, IncidenceMongoWeight.class).byDefault().register();
        mapperFactory.classMap(Farm.class, FarmMongo.class).byDefault().register();
        mapperFactory.classMap(Building.class, BuildingMongo.class).byDefault().register();
        mapperFactory.classMap(Division.class, DivisionMongo.class).byDefault().register();
        mapperFactory.classMap(MarketHistory.class, MarketHistoryMongo.class).byDefault().register();
        mapperFactory.classMap(Animal.class, AnimalMongo.class).byDefault().register();
        mapperFactory.classMap(Race.class, RaceMongo.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public static MapperFacade getInstance(){
        if (mapper == null){
            mapper = new Mapper();
        }
        return mapper.mapperFacade;
    }
}
